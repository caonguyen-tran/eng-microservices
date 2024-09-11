package com.engapp.QuizService.controller.admin;

import com.engapp.QuizService.dto.request.AnswerRequest;
import com.engapp.QuizService.dto.response.ApiStructResponse;
import com.engapp.QuizService.pojo.Answer;
import com.engapp.QuizService.pojo.Question;
import com.engapp.QuizService.service.AnswerService;
import com.engapp.QuizService.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/admin/answer")
public class AdminAnswerController {
    @Autowired
    private AnswerService answerService;

    @Autowired
    private QuestionService questionService;

    @PostMapping(value="/create")
    public ApiStructResponse<Answer> createAnswer(@RequestBody AnswerRequest answerRequest) {
        Question question = this.questionService.getQuestionById(answerRequest.getQuestionIdRequest());
        answerRequest.setQuestion(question);
        Answer answer = this.answerService.createAnswer(answerRequest);

        return ApiStructResponse.<Answer>builder()
                .message("create answer from admin")
                .data(answer)
                .build();
    }

    @PostMapping(value="/create-multiple")
    public ApiStructResponse<List<Answer>> createMultipleAnswer(@RequestBody List<AnswerRequest> answerRequestList) {
        List<Answer> answerList = this.answerService.createMultipleAnswers(answerRequestList);

        return ApiStructResponse.<List<Answer>>builder()
                .message("create multiple answers from admin")
                .data(answerList)
                .build();
    }
}
