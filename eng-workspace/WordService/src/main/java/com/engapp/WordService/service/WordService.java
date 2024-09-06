package com.engapp.WordService.service;

import com.engapp.WordService.dto.request.WordRequest;
import com.engapp.WordService.dto.request.WordUpdateRequest;
import com.engapp.WordService.dto.response.WordResponse;
import com.engapp.WordService.pojo.Word;

import java.util.List;

public interface WordService {

    Word createWord(Word word);

    public List<Word> getListWordByCollectionId(String collectionId);

    public Word updateWord(Word word, WordUpdateRequest wordUpdateRequest);

    public String deleteWord(Word word);

    public Word getWordById(String id);
}
