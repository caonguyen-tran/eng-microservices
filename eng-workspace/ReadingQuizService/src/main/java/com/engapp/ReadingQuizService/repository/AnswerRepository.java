package com.engapp.ReadingQuizService.repository;

import com.engapp.ReadingQuizService.pojo.Answer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends MongoRepository<Answer, String> {
}
