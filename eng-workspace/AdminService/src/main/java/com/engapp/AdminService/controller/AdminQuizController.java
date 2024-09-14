package com.engapp.AdminService.controller;

import com.engapp.AdminService.dto.clone.QuizClone.QuestionSetResponseClone;
import com.engapp.AdminService.dto.response.ApiStructResponse;
import com.engapp.AdminService.dto.response.UserResponse.UserResponse;
import com.engapp.AdminService.feign.QuizClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/admin/quiz")
public class AdminQuizController {
    @Autowired
    private QuizClient quizClient;

    @GetMapping("/get-list")
    ApiStructResponse<List<QuestionSetResponseClone>> getQuizList(
            @RequestParam(defaultValue = "0") Integer pageNo
            , @RequestParam(defaultValue = "10") Integer pageSize
            ,@RequestParam(defaultValue = "id") String sortBy){
        return quizClient.getAllQuestionSets(pageNo, pageSize, sortBy);
    }
}
