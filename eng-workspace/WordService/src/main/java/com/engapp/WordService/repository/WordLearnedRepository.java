package com.engapp.WordService.repository;

import com.engapp.WordService.pojo.WordLearned;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WordLearnedRepository extends MongoRepository<WordLearned, String> {

    @Query("{'learnBy': ?0, 'wordResponse._id': ?1}")
    public WordLearned findByUserAndWord(String learnBy, String word);
}
