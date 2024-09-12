package com.engapp.AdminService.controller;

import com.engapp.AdminService.dto.response.ApiStructResponse;
import com.engapp.AdminService.dto.response.WordResponse.WordLearnedResponse;
import com.engapp.AdminService.dto.response.WordResponse.WordResponse;
import com.engapp.AdminService.feign.WordClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/word")
public class AdminWordController {
    @Autowired
    private WordClient wordClient;

    @GetMapping(value="/list-word")
    public ApiStructResponse<List<WordResponse>> listWord(){
        return this.wordClient.listWord();
    }

    @GetMapping(value="/list-learned")
    public ApiStructResponse<List<WordLearnedResponse>> listLearnedWord(){
        return this.wordClient.listLearnedWord();
    }
}
