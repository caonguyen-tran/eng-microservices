package com.engapp.ReadingQuizService.repository;

import com.engapp.ReadingQuizService.pojo.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    @Query(value = "select q from Question q where q.id = :questionId and q.questionNumber = :questionNumber")
    Question findByQuestionNumber(int questionId, int questionNumber);

    @Query(value="select q from Question q where q.questionSet.id = :questionSetId")
    List<Question> getQuestionsByQuestionSetId(int questionSetId);
}
