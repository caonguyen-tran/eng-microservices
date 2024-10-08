package com.engapp.QuizService.repository;

import com.engapp.QuizService.pojo.ExamResponses;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ExamResponseRepository extends JpaRepository<ExamResponses, Integer> {

    @Query("select e from ExamResponses e where e.result.id = :resultId")
    List<ExamResponses> findByResultId(int resultId);

    @Query("select e from ExamResponses e where e.id = :id")
    Optional<ExamResponses> findByExamId(int id);
}
