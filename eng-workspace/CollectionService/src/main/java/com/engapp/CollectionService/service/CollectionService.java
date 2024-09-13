package com.engapp.CollectionService.service;

import com.engapp.CollectionService.dto.request.CollectionRequest;
import com.engapp.CollectionService.pojo.Collection;

import java.util.List;

public interface CollectionService {
    Collection createCollection(Collection collection);

    Collection getCollectionById(String id);

    List<Collection> getCollectionByCreatedBy(String userId, Integer pageNo, Integer pageSize, String sortBy);

    List<Collection> getCollectionByName(String collectionName);

    void deleteCollectionById(Collection collection);

    Collection updateCollection(Collection collection);

    boolean inspectCollectionOwner(Collection collection);

    List<Collection> getAllCollections();

    List<Collection> getCollectionsByParams(Integer pageNo, Integer pageSize, String sortBy);

    Collection updateCollectionByAdmin(Collection collection);

    void deleteCollectionByAdmin(String id);
}
