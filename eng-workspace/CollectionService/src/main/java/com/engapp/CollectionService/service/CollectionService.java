package com.engapp.CollectionService.service;

import com.engapp.CollectionService.dto.request.CollectionRequest;
import com.engapp.CollectionService.pojo.Collection;

import java.util.List;

public interface CollectionService {
    Collection createCollection(CollectionRequest collectionRequest);

    Collection getCollectionById(String id);

    List<Collection> getCollectionByCreatedBy(String createdBy);

    List<Collection> getCollectionByName(String collectionName);

    void deleteCollectionById(Collection collection);

    Collection updateCollection(CollectionRequest collectionRequest, Collection collection);
}
