package com.engapp.WordService.repository;

import com.engapp.WordService.pojo.Word;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepository extends MongoRepository<Word, String> {

    @Query("{'collectionId': ?0}")
    public List<Word> getListWordByCollectionId(String collectionId);
}
