package com.engapp.WordService.service;

import com.engapp.WordService.dto.request.WordLearnedCreateRequest;
import com.engapp.WordService.dto.request.WordLearnedUpdateRequest;
import com.engapp.WordService.pojo.WordLearned;

import java.util.List;

public interface WordLearnedService {

    WordLearned createWordLearned(WordLearnedCreateRequest wordLearnedCreateRequest);

    WordLearned isExistWordLearned(String learnBy, String wordId);

    List<WordLearned> updateListWordLearned(List<WordLearnedUpdateRequest> wordLearnedUpdateRequests);

    List<WordLearned> createListWordLearned(List<WordLearnedCreateRequest> wordLearnedCreateRequestList);

    WordLearned updateWordLearned(WordLearned wordLearned);

    WordLearned getWordLearnedById(String wordLearnedId);
}
