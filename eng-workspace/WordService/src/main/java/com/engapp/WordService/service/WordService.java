package com.engapp.WordService.service;

import com.engapp.WordService.dto.request.WordRequest;
import com.engapp.WordService.dto.response.WordResponse;
import com.engapp.WordService.pojo.Word;

import java.util.List;

public interface WordService {

    Word createWord(Word word);

    public List<Word> getListWordByCollectionId(String collectionId);
}
