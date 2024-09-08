package com.engapp.WordService.controller.admin;

import com.engapp.WordService.dto.response.ApiStructResponse;
import com.engapp.WordService.dto.response.WordLearnedResponse;
import com.engapp.WordService.mapper.WordLearnedMapper;
import com.engapp.WordService.pojo.WordLearned;
import com.engapp.WordService.service.WordLearnedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/admin/learned-word")
public class AdminWordLearnedController {
    @Autowired
    private WordLearnedService wordLearnedService;

    @Autowired
    private WordLearnedMapper wordLearnedMapper;

    @GetMapping(value="/list")
    public ApiStructResponse<List<WordLearnedResponse>> list() {
        List<WordLearned> listWordLearned = this.wordLearnedService.getAllByAdmin();
        List<WordLearnedResponse> listWordResponses = listWordLearned.stream().map(item -> this.wordLearnedMapper.wordLearnedToWordLearnedResponse(item)).toList();
        return ApiStructResponse.<List<WordLearnedResponse>>builder()
                .message("List word is given by the admin")
                .data(listWordResponses)
                .build();
    }
}
