package com.engapp.ReadingQuizService.repository;

import com.engapp.ReadingQuizService.pojo.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface AnswerRepository extends JpaRepository<Answer, Integer> {

    @Query(value="select a from Answer a where a.question.id = :questionId")
    List<Answer> findByQuestionId(Integer questionId);
}
