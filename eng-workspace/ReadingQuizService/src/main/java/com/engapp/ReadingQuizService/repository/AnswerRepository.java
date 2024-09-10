package com.engapp.ReadingQuizService.repository;

import com.engapp.ReadingQuizService.pojo.Answer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface AnswerRepository extends MongoRepository<Answer, String> {

    @Query("{'questionId':  ?0}")
    List<Answer> findByQuestionId(String questionId);
}
