package com.engapp.QuizService.controller;

import com.engapp.QuizService.dto.response.ApiStructResponse;
import com.engapp.QuizService.dto.response.QuestionSetResponse;
import com.engapp.QuizService.pojo.QuizResult;
import com.engapp.QuizService.service.QuizResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value="/get-result/{resultId}")
    public ApiStructResponse<QuizResult> getQuizResultById(@PathVariable int resultId) {
        QuizResult quizResult = this.quizResultService.findById(resultId);

        return ApiStructResponse.<QuizResult>builder()
                .message("Get quiz result by id")
                .data(quizResult)
                .build();
    }
}
