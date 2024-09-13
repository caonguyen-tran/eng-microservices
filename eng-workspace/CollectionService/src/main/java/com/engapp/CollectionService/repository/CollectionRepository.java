package com.engapp.CollectionService.repository;

import com.engapp.CollectionService.pojo.Collection;
import com.engapp.CollectionService.pojo.Download;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionRepository extends MongoRepository<Collection, String> {

    public List<Collection> findByCreateBy(String createBy);

    @Query("{'name': {$regex: ?0}}")
    public List<Collection> findByCollectionName(String collectionName);

    @Query("{'createBy': ?0}")
    Slice<Collection> findCollectionsByCreatedBy(String createdBy, Pageable pageable);
}
