package com.engapp.CollectionService.service.implement;

import com.engapp.CollectionService.configuration.CustomUserDetails;
import com.engapp.CollectionService.dto.request.CollectionRequest;
import com.engapp.CollectionService.exception.ApplicationException;
import com.engapp.CollectionService.exception.ErrorCode;
import com.engapp.CollectionService.mapper.CollectionMapper;
import com.engapp.CollectionService.pojo.Collection;
import com.engapp.CollectionService.repository.CollectionRepository;
import com.engapp.CollectionService.service.CollectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@Slf4j
public class CollectionServiceImplement implements CollectionService {
    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private CollectionMapper collectionMapper;

    @Override
    public Collection createCollection(CollectionRequest collectionRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        Collection collection = this.collectionMapper.collectionRequestToCollection(collectionRequest);
        collection.setCreateAt(Instant.now());
        collection.setUpdateAt(Instant.now());
        collection.setCreateBy(userDetails.getId());
        return this.collectionRepository.save(collection);
    }

    @Override
    public Collection getCollectionById(String id) {
        return this.collectionRepository.findById(id).orElseThrow(() -> new ApplicationException(ErrorCode.NOT_EXIST));
    }

    @Override
    public List<Collection> getCollectionByCreatedBy(String createdBy) {
        return this.collectionRepository.findByCreateBy(createdBy);
    }

    @Override
    public List<Collection> getCollectionByName(String collectionName) {
        return this.collectionRepository.findByCollectionName(collectionName);
    }

    @Override
    public void deleteCollectionById(Collection collection) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        if(!collection.getCreateBy().equals(userDetails.getId())) {
            throw new ApplicationException(ErrorCode.NOT_ACCEPTABLE);
        }
        this.collectionRepository.delete(collection);
    }

    @Override
    public Collection updateCollection(CollectionRequest collectionRequest, Collection collection) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        if(!collection.getCreateBy().equals(userDetails.getId())) {
            throw new ApplicationException(ErrorCode.NOT_ACCEPTABLE);
        }
        collection.setUpdateAt(Instant.now());
        collection.setName(collectionRequest.getName());
        collection.setDescription(collectionRequest.getDescription());
        return this.collectionRepository.save(collection);
    }

}
