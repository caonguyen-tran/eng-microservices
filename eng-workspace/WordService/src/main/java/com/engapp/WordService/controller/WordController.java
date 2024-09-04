package com.engapp.WordService.controller;

import com.engapp.WordService.dto.request.WordRequest;
import com.engapp.WordService.dto.response.ApiStructResponse;
import com.engapp.WordService.dto.response.WordResponse;
import com.engapp.WordService.mapper.WordMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(value="/word")
public class WordController {
    @Autowired
    private WordMapper wordMapper;


    @GetMapping(value="/")
    public String getWord() {
        return "hello word! I'm a word service!";
    }


    @PostMapping(value="/create")
    public ApiStructResponse<WordResponse> createWord(@RequestBody WordRequest wordRequest) {

        return ApiStructResponse.<WordResponse>builder()
                .message("Create word successfully.")
                .data(null)
                .build();
    }
}
