package com.engapp.ReadingQuizService.repository;

import com.engapp.ReadingQuizService.pojo.QuestionSet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface QuestionSetRepository extends MongoRepository<QuestionSet, String> {
    @Query("{'readingPart':  ?0}")
    List<QuestionSet> filterByReadingPart(int readingPart);

    @Query("{'readingPart': ?0, 'yearOf': ?1}")
    List<QuestionSet> filterByReadingPartAndYearOf(int readingPart, int yearOf);
}
