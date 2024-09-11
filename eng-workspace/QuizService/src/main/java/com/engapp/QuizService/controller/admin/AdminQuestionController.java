package com.engapp.QuizService.controller.admin;

import com.engapp.QuizService.dto.request.QuestionRequest;
import com.engapp.QuizService.dto.request.update.QuestionUpdateRequest;
import com.engapp.QuizService.dto.response.ApiStructResponse;
import com.engapp.QuizService.dto.response.QuestionResponse;
import com.engapp.QuizService.mapper.QuestionMapper;
import com.engapp.QuizService.pojo.Question;
import com.engapp.QuizService.pojo.QuestionSet;
import com.engapp.QuizService.service.QuestionService;
import com.engapp.QuizService.service.QuestionSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/admin/question")
public class AdminQuestionController {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionSetService questionSetService;

    @PostMapping(value="/create")
    public ApiStructResponse<QuestionResponse> create(@RequestBody QuestionRequest questionRequest) {
        QuestionSet questionSet = this.questionSetService.getQuestionSetById(questionRequest.getQuestionSetIdRequest());
        questionRequest.setQuestionSet(questionSet);
        Question question = this.questionService.createQuestion(questionRequest);
        QuestionResponse questionResponse = this.questionMapper.questionToQuestionResponse(question);

        return ApiStructResponse.<QuestionResponse>builder()
                .message("create question from admin")
                .data(questionResponse)
                .build();
    }

    @PutMapping(value="/update")
    public ApiStructResponse<QuestionResponse> update(@RequestBody QuestionUpdateRequest questionUpdateRequest) {
        Question question = this.questionService.updateQuestion(questionUpdateRequest);
        QuestionResponse questionResponse = this.questionMapper.questionToQuestionResponse(question);

        return ApiStructResponse.<QuestionResponse>builder()
                .message("update question from admin")
                .data(questionResponse)
                .build();
    }

    @GetMapping(value="/get-all")
    public ApiStructResponse<List<QuestionResponse>> getAll() {
        List<Question> questions = this.questionService.getAllQuestions();

        List<QuestionResponse> questionResponseList = questions
                .stream()
                .map(item -> this.questionMapper.questionToQuestionResponse(item)).toList();

        return ApiStructResponse.<List<QuestionResponse>>builder()
                .message("List all questions from admin")
                .data(questionResponseList)
                .build();
    }
}
