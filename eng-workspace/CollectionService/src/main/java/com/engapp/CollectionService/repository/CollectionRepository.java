package com.engapp.CollectionService.repository;

import com.engapp.CollectionService.pojo.Collection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionRepository extends MongoRepository<Collection, String> {
}
