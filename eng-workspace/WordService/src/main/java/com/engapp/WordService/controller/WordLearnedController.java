package com.engapp.WordService.controller;

import com.engapp.WordService.dto.request.WordLearnedCreateRequest;
import com.engapp.WordService.dto.request.WordLearnedUpdateRequest;
import com.engapp.WordService.dto.response.ApiStructResponse;
import com.engapp.WordService.dto.response.WordLearnedResponse;
import com.engapp.WordService.mapper.WordLearnedMapper;
import com.engapp.WordService.pojo.WordLearned;
import com.engapp.WordService.service.WordLearnedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value="/learned-word")
public class WordLearnedController {
    @Autowired
    private WordLearnedService wordLearnedService;

    @Autowired
    private WordLearnedMapper wordLearnedMapper;

    @PostMapping(value="/create")
    public ApiStructResponse<List<WordLearnedResponse>> createWordLearned(@RequestBody List<WordLearnedCreateRequest> wordLearnedCreateRequestList) {
        List<WordLearned> listWordLearned = this.wordLearnedService.createListWordLearned(wordLearnedCreateRequestList);

        List<WordLearnedResponse> listWordLearnedResponse = listWordLearned
                .stream()
                .map(item -> this.wordLearnedMapper.wordLearnedToWordLearnedResponse(item))
                .toList();

        return ApiStructResponse.<List<WordLearnedResponse>>builder()
                .message("Create word learned.")
                .data(listWordLearnedResponse)
                .build();
    }

    @PatchMapping(value="/update-list-word")
    public ApiStructResponse<List<WordLearnedResponse>> updateListWord(@RequestBody List<WordLearnedUpdateRequest> listWordLearnedUpdateRequest) {
        List<WordLearned> wordLearnedList = this.wordLearnedService.updateListWordLearned(listWordLearnedUpdateRequest);
        List<WordLearnedResponse> listWordLearnedResponse = wordLearnedList
                .stream()
                .map(item -> this.wordLearnedMapper.wordLearnedToWordLearnedResponse(item))
                .toList();

        return ApiStructResponse.<List<WordLearnedResponse>>builder()
                .message("Update word list.")
                .data(listWordLearnedResponse)
                .build();
    }
}
