package com.engapp.ReadingQuizService.repository;

import com.engapp.ReadingQuizService.pojo.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface QuestionRepository extends MongoRepository<Question, String> {

    @Query("{'questionNumber': ?0}")
    Question findByQuestionNumber(int questionNumber);

    @Query("{'questionSetId': ?0}")
    List<Question> getQuestionsByQuestionSetId(String questionSetId);
}
