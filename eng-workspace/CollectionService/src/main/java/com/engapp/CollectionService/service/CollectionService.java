package com.engapp.CollectionService.service;

import com.engapp.CollectionService.dto.request.CollectionRequest;
import com.engapp.CollectionService.pojo.Collection;

public interface CollectionService {
    Collection createCollection(CollectionRequest collectionRequest);
}
