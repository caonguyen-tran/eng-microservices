package com.engapp.WordService.repository;

import com.engapp.WordService.pojo.Word;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends MongoRepository<Word, String> {
}
