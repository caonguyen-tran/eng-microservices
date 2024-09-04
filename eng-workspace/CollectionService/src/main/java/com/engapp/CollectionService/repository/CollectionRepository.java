package com.engapp.CollectionService.repository;

import com.engapp.CollectionService.pojo.Collection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionRepository extends MongoRepository<Collection, String> {

    public List<Collection> findByCreateBy(String createBy);

    @Query("{'name': {$regex: ?0}}")
    public List<Collection> findByCollectionName(String collectionName);
}
