package com.engapp.CollectionService.service.implement;

import com.engapp.CollectionService.configuration.CustomUserDetails;
import com.engapp.CollectionService.configuration.PrincipalConfiguration;
import com.engapp.CollectionService.dto.request.CollectionRequest;
import com.engapp.CollectionService.exception.ApplicationException;
import com.engapp.CollectionService.exception.ErrorCode;
import com.engapp.CollectionService.pojo.Collection;
import com.engapp.CollectionService.repository.CollectionRepository;
import com.engapp.CollectionService.service.CollectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@Slf4j
public class CollectionServiceImplement implements CollectionService {
    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private PrincipalConfiguration principalConfiguration;

    @Override
    public Collection createCollection(Collection collection) {
        CustomUserDetails userDetails = this.principalConfiguration.getCustomUserDetails();

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
        CustomUserDetails userDetails = this.principalConfiguration.getCustomUserDetails();

        if(!collection.getCreateBy().equals(userDetails.getId())) {
            throw new ApplicationException(ErrorCode.NOT_ACCEPTABLE);
        }
        this.collectionRepository.delete(collection);
    }

    @Override
    public Collection updateCollection(CollectionRequest collectionRequest, Collection collection) {
        CustomUserDetails userDetails = this.principalConfiguration.getCustomUserDetails();

        if(!collection.getCreateBy().equals(userDetails.getId())) {
            throw new ApplicationException(ErrorCode.NOT_ACCEPTABLE);
        }
        collection.setUpdateAt(Instant.now());
        collection.setName(collectionRequest.getName());
        collection.setDescription(collectionRequest.getDescription());
        return this.collectionRepository.save(collection);
    }

    @Override
    public boolean inspectCollectionOwner(Collection collection) {
        CustomUserDetails userDetails = this.principalConfiguration.getCustomUserDetails();
        return collection.getCreateBy().equals(userDetails.getId());
    }

    @Override
    public List<Collection> getAllCollections() {
        return this.collectionRepository.findAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public Collection updateCollectionByAdmin(Collection collection, CollectionRequest collectionRequest) {
        collection.setName(collectionRequest.getName());
        collection.setDescription(collectionRequest.getDescription());
        collection.setUpdateAt(Instant.now());
        return this.collectionRepository.save(collection);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public void deleteCollectionByAdmin(String id) {
        this.collectionRepository.deleteById(id);
    }
}
