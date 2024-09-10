package com.engapp.ReadingQuizService.controller.admin;

import com.engapp.ReadingQuizService.dto.request.AnswerRequest;
import com.engapp.ReadingQuizService.dto.response.ApiStructResponse;
import com.engapp.ReadingQuizService.pojo.Answer;
import com.engapp.ReadingQuizService.service.AnswerService;
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

    @PostMapping(value="/create")
    public ApiStructResponse<Answer> createAnswer(@RequestBody AnswerRequest answerRequest) {
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
