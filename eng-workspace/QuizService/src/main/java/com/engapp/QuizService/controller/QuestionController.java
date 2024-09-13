package com.engapp.QuizService.controller;

import com.engapp.QuizService.dto.response.ApiStructResponse;
import com.engapp.QuizService.pojo.Question;
import com.engapp.QuizService.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping(value="/list-by-question-set/{questionSetId}")
    public ApiStructResponse<List<Question>> getListQuestion(@PathVariable int questionSetId){
        List<Question> questionList = this.questionService.getByQuestionSetId(questionSetId);

        return ApiStructResponse.<List<Question>>builder()
                .message("get list question by question set id")
                .data(questionList)
                .build();
    }
}
