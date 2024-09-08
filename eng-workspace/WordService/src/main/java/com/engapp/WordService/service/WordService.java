package com.engapp.WordService.service;

import com.engapp.WordService.dto.request.WordRequest;
import com.engapp.WordService.dto.request.WordUpdateRequest;
import com.engapp.WordService.dto.response.WordResponse;
import com.engapp.WordService.pojo.Word;

import java.util.List;

public interface WordService {

    Word createWord(Word word);

    List<Word> getListWordByCollectionId(String collectionId);

    Word updateWord(Word word, WordUpdateRequest wordUpdateRequest);

    String deleteWord(Word word);

    Word getWordById(String id);

    List<Word> getAllByAdmin();
}
