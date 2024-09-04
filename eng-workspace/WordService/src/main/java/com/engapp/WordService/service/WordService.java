package com.engapp.WordService.service;

import com.engapp.WordService.pojo.Word;

import java.util.List;

public interface WordService {

    Word createWord(Word word);

    public List<Word> getListWordFromCollectionId(String collectionId);
}
