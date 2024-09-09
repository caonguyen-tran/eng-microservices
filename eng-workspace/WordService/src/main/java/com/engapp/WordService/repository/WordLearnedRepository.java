package com.engapp.WordService.repository;

import com.engapp.WordService.pojo.WordLearned;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface WordLearnedRepository extends MongoRepository<WordLearned, String> {

    @Query("{'learnBy': ?0, 'wordResponse._id': ?1}")
    WordLearned findByUserAndWord(String learnBy, String word);

    @Query("{'learnBy':  ?0}")
    List<WordLearned> filterAllByLearnedBy(String learnBy);

    @Query("{'learnBy':  ?0, 'isReview' :  ?1}")
    List<WordLearned> filterByReview(String learnBy, boolean isReview);

    @Query("{'learnBy' : ?0, 'isLearn':  ?1}")
    List<WordLearned> filterByLearnByAndIsLearned(String learnBy, boolean isLearned);

    @Query("{'learnBy':  ?0, 'learnedMaster.key':  ?1}")
    List<WordLearned> filterByMasterLevel(String learnBy, int masterLevel);

    @Query("{'dueDate':  {$lte:  ?0}, 'isLearn': true}")
    List<WordLearned> filterByDueDateLessThanOrEqual(Instant instant);
}
