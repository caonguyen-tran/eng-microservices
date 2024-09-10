package com.engapp.ReadingQuizService.repository;

import com.engapp.ReadingQuizService.pojo.Quiz;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends MongoRepository<Quiz, String> {
}
