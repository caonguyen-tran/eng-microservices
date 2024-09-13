package com.engapp.QuizService.repository;

import com.engapp.QuizService.pojo.QuizResult;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface QuizResultRepository extends JpaRepository<QuizResult, Integer> {

    @Query("select q from QuizResult q where q.userId = :userId and q.questionSet.id = :questionSet")
    QuizResult findByUserAndQuestionSet(String userId, int questionSet);

    @Query("select q from QuizResult q where q.userId = :userId")
    List<QuizResult> listByUserId(String userId);
}
