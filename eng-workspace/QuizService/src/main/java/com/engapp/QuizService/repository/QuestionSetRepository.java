package com.engapp.QuizService.repository;

import com.engapp.QuizService.pojo.QuestionSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface QuestionSetRepository extends JpaRepository<QuestionSet, Integer> {
    @Query(value = "select q from QuestionSet q where q.readingPart = :readingPart")
    List<QuestionSet> filterByReadingPart(int readingPart);

    @Query(value = "select q from QuestionSet q where q.readingPart = :readingPart and q.yearOf = :yearOf")
    List<QuestionSet> filterByReadingPartAndYearOf(int readingPart, int yearOf);

    @Query(value = "select q from QuestionSet q where q.name like %:name%")
    List<QuestionSet> filterByName(String name);
}
