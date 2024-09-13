package com.engapp.QuizService.controller;

import com.engapp.QuizService.dto.response.ApiStructResponse;
import com.engapp.QuizService.dto.response.QuestionSetResponse;
import com.engapp.QuizService.pojo.QuizResult;
import com.engapp.QuizService.service.QuizResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/quiz-result")
public class QuizResultController {
    @Autowired
    private QuizResultService quizResultService;

    @GetMapping(value="/get-list-by-owner")
    public ApiStructResponse<List<QuizResult>> getListQuizResultByOwner() {
        List<QuizResult> quizResults = this.quizResultService.getByOwner();

        return ApiStructResponse.<List<QuizResult>>builder()
                .message("List of quiz result by owner.")
                .data(quizResults)
                .build();
    }
}
