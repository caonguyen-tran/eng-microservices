package com.engapp.WordService.controller;

import com.engapp.WordService.dto.request.WordRequest;
import com.engapp.WordService.dto.request.WordUpdateRequest;
import com.engapp.WordService.dto.response.ApiStructResponse;
import com.engapp.WordService.dto.response.WordResponse;
import com.engapp.WordService.feign.CollectionClient;
import com.engapp.WordService.mapper.WordMapper;
import com.engapp.WordService.pojo.Word;
import com.engapp.WordService.service.WordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/word")
public class WordController {
    @Autowired
    private WordService wordService;

    @Autowired
    private WordMapper wordMapper;

    @GetMapping(value = "/")
    public String getWord() {
        return "hello world! I'm a word service!";
    }


    @PostMapping(value = "/create")
    public ApiStructResponse<WordResponse> createWord(@RequestBody WordRequest wordRequest) {
        Word word = this.wordService.createWord(this.wordMapper.wordRequestToWord(wordRequest));
        WordResponse wordResponse = this.wordMapper.wordToWordResponse(word);
        return ApiStructResponse.<WordResponse>builder()
                .message("Create word successfully.")
                .data(wordResponse)
                .build();
    }

    @GetMapping(value = "/list-words")
    public ApiStructResponse<List<WordResponse>> listWords(@RequestParam(value = "collectionId") String collectionId
            , @RequestParam(defaultValue = "0") Integer pageNo
            , @RequestParam(defaultValue = "15") Integer pageSize
            , @RequestParam(defaultValue = "id") String sortBy) {
        List<Word> lists = this.wordService.getListWordByCollectionIdAndParams(collectionId, pageNo, pageSize, sortBy);
        List<WordResponse> wordResponses = lists
                .stream()
                .map(item -> this.wordMapper.wordToWordResponse(item))
                .toList();

        return ApiStructResponse.<List<WordResponse>>builder()
                .message("Get List words by collectionId successfully.")
                .data(wordResponses)
                .build();
    }

    @PutMapping(value = "/update-word")
    public ApiStructResponse<WordResponse> updateWord(@RequestBody WordUpdateRequest wordUpdateRequest) {
        Word word = this.wordService.getWordById(wordUpdateRequest.getId());
        Word wordUpdate = this.wordService.updateWord(word, wordUpdateRequest);
        return ApiStructResponse.<WordResponse>builder()
                .message("Update word successfully.")
                .data(this.wordMapper.wordToWordResponse(wordUpdate))
                .build();
    }

    @DeleteMapping(value = "/delete")
    public ApiStructResponse<String> delete(@RequestParam(value = "wordId") String wordId) {
        Word word = this.wordService.getWordById(wordId);
        String result = this.wordService.deleteWord(word);
        return ApiStructResponse.<String>builder()
                .message("Delete word successfully.")
                .data(result)
                .build();
    }
}
