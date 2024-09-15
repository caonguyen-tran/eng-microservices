package com.engapp.AdminService.controller;

import com.engapp.AdminService.dto.response.QuizResponse.QuestionResponse;
import com.engapp.AdminService.dto.response.QuizResponse.QuestionSetResponse;
import com.engapp.AdminService.dto.response.ApiStructResponse;
import com.engapp.AdminService.feign.QuizClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value="/admin/quiz")
public class AdminQuizController {
    @Autowired
    private QuizClient quizClient;

    @GetMapping("/get-list-question-set")
    ApiStructResponse<List<QuestionSetResponse>> getQuizList(
            @RequestParam(defaultValue = "0") Integer pageNo
            , @RequestParam(defaultValue = "10") Integer pageSize
            ,@RequestParam(defaultValue = "id") String sortBy){
        return quizClient.getAllQuestionSets(pageNo, pageSize, sortBy);
    }

    @GetMapping(value="/get-list-question")
    ApiStructResponse<List<QuestionResponse>> getQuestion(){
        return quizClient.getAllQuestion();
    }

    @PostMapping(value="/create-multiple-question/{questionSetId}")
    ApiStructResponse<List<QuestionResponse>> createMultipleQuestion(
            @PathVariable(value="questionSetId") Integer questionSetId
            ,@RequestPart(value="file") MultipartFile file){
        return quizClient.createMultipleQuestion(questionSetId, file);
    }
}
