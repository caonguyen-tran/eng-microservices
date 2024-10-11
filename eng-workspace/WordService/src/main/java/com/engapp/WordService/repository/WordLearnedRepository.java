package com.engapp.WordService.repository;

import com.engapp.WordService.pojo.WordLearned;
import org.springframework.data.domain.Pageable;
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

    @Query("{'learnBy':  ?0, 'isReview' :  ?1, 'isLearn': ?2}")
    List<WordLearned> filterByReviewAndLearned(String learnBy, boolean isReview, boolean isLearn);

    @Query("{'learnBy': ?0, 'wordResponse.collectionId': ?1}")
    List<WordLearned> filterByCollectionIdAndLearnBy(String learnBy, String collectionId);

    @Query("{'learnBy': ?0, 'isReview': ?1}")
    List<WordLearned> filterTop5ByReviewAndIsReview(String learnBy, boolean isReview, Pageable pageable);

    @Query("{'learnBy': ?0, 'isReview': ?1, 'isLearn': ?2, 'wordResponse.collectionId':  ?3}")
    List<WordLearned> filterNonActiveInCollection(String learnBy, boolean isReview, boolean isLearn, String collectionId);

    @Query("{downloadId:  ?0}")
    List<WordLearned> filterByDownloadId(String downloadId);
}
