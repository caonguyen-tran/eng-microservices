package com.engapp.ReadingQuizService.repository;

import com.engapp.ReadingQuizService.pojo.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {
}
