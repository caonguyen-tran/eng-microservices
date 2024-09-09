package com.engapp.WordService.service;

import com.engapp.WordService.dto.request.WordLearnedRequest;
import com.engapp.WordService.pojo.Word;
import com.engapp.WordService.pojo.WordLearned;

import java.time.Instant;
import java.util.List;

public interface WordLearnedService {

    WordLearned createWordLearned(WordLearnedRequest wordLearnedRequest);

    WordLearned isExistWordLearned(String learnBy, String wordId);

    List<WordLearned> updateListWordLearned(List<WordLearnedRequest> wordLearnedRequests);

    List<WordLearned> createListWordLearned(List<WordLearnedRequest> wordLearnedRequests);

    WordLearned updateWordLearned(WordLearned wordLearned);

    WordLearned getWordLearnedById(String wordLearnedId);

    void downloadListWordInCollection(List<Word> wordList, String userId);

    List<WordLearned> filterAllByLearnedBy();

    List<WordLearned> filterByReview(boolean isReview);

    List<WordLearned> filterByMasterLevel(int master);

    List<WordLearned> filterByLearnByAndIsLearned(boolean isLearned);

    List<WordLearned> getAllByAdmin();

    List<WordLearned> filterByDueDateLessThanOrEqual(Instant instant);

    void updateReviewStatus(WordLearned wordLearned);
}
