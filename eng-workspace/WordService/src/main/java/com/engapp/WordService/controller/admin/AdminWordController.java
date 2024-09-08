package com.engapp.WordService.controller.admin;

import com.engapp.WordService.dto.response.ApiStructResponse;
import com.engapp.WordService.dto.response.WordResponse;
import com.engapp.WordService.mapper.WordMapper;
import com.engapp.WordService.pojo.Word;
import com.engapp.WordService.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/admin/word")
public class AdminWordController {
    @Autowired
    private WordService wordService;

    @Autowired
    private WordMapper wordMapper;

    @GetMapping(value="/list")
    public ApiStructResponse<List<WordResponse>> list() {
        List<Word> listWords = this.wordService.getAllByAdmin();
        List<WordResponse> listWordResponses = listWords.stream().map(item -> this.wordMapper.wordToWordResponse(item)).toList();
        return ApiStructResponse.<List<WordResponse>>builder()
                .message("List word is given by the admin")
                .data(listWordResponses)
                .build();
    }
}
