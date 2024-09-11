package com.engapp.ReadingQuizService.controller.admin;

import com.engapp.ReadingQuizService.dto.request.QuestionRequest;
import com.engapp.ReadingQuizService.dto.request.update.QuestionUpdateRequest;
import com.engapp.ReadingQuizService.dto.response.ApiStructResponse;
import com.engapp.ReadingQuizService.dto.response.QuestionResponse;
import com.engapp.ReadingQuizService.mapper.QuestionMapper;
import com.engapp.ReadingQuizService.pojo.Question;
import com.engapp.ReadingQuizService.pojo.QuestionSet;
import com.engapp.ReadingQuizService.service.QuestionService;
import com.engapp.ReadingQuizService.service.QuestionSetService;
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
        Question question = this.questionService.createQuestion(questionRequest);
        QuestionSet questionSet = this.questionSetService.getQuestionSetById(questionRequest.getQuestionSetIdRequest());
        question.setQuestionSet(questionSet);
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
