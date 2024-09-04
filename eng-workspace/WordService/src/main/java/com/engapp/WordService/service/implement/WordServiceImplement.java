package com.engapp.WordService.service.implement;

import com.engapp.WordService.pojo.Word;
import com.engapp.WordService.service.WordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class WordServiceImplement implements WordService {

    @Override
    public Word createWord(Word word) {
        return null;
    }

    @Override
    public List<Word> getListWordFromCollectionId(String collectionId) {
        return List.of();
    }
}
