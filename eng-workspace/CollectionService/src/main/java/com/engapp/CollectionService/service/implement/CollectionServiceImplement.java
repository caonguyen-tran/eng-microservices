package com.engapp.CollectionService.service.implement;

import com.engapp.CollectionService.dto.request.CollectionRequest;
import com.engapp.CollectionService.mapper.CollectionMapper;
import com.engapp.CollectionService.pojo.Collection;
import com.engapp.CollectionService.pojo.CustomUserDetails;
import com.engapp.CollectionService.repository.CollectionRepository;
import com.engapp.CollectionService.service.CollectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;

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
}
