package com.engapp.ReadingQuizService.controller.admin;

import com.engapp.ReadingQuizService.dto.request.QuestionSetRequest;
import com.engapp.ReadingQuizService.dto.request.update.QuestionSetUpdateRequest;
import com.engapp.ReadingQuizService.dto.response.ApiStructResponse;
import com.engapp.ReadingQuizService.dto.response.QuestionSetResponse;
import com.engapp.ReadingQuizService.mapper.QuestionSetMapper;
import com.engapp.ReadingQuizService.pojo.QuestionSet;
import com.engapp.ReadingQuizService.service.QuestionSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/admin/question-set")
public class AdminQuestionSetController {
    @Autowired
    private QuestionSetService questionSetService;

    @Autowired
    private QuestionSetMapper questionSetMapper;

    @PostMapping(value="/create")
    public ApiStructResponse<QuestionSetResponse> create(@RequestBody QuestionSetRequest questionSetRequest) {
        QuestionSet questionSet = questionSetMapper.questionSetRequestToQuestionSet(questionSetRequest);
        QuestionSetResponse questionSetResponse = this.questionSetMapper.questionSetToQuestionSetResponse(this.questionSetService.createQuestionSet(questionSet));
        return ApiStructResponse.<QuestionSetResponse>builder()
                .message("create question set by admin")
                .data(questionSetResponse)
                .build();
    }

    @PutMapping(value="update")
    public ApiStructResponse<QuestionSetResponse> update(@RequestBody QuestionSetUpdateRequest questionSetUpdateRequest) {
        QuestionSet questionSet = this.questionSetService.updateQuestionSet(questionSetUpdateRequest);
        QuestionSetResponse questionSetResponse = this.questionSetMapper.questionSetToQuestionSetResponse(questionSet);
        return ApiStructResponse.<QuestionSetResponse>builder()
                .message("update question set by admin")
                .data(questionSetResponse)
                .build();
    }

    @GetMapping(value="/get-all")
    public ApiStructResponse<List<QuestionSetResponse>> getAllQuestionSets(){
        List<QuestionSet> questionSetList = this.questionSetService.getAllQuestionSets();

        List<QuestionSetResponse> questionSetResponseList = questionSetList
                .stream()
                .map(item -> this.questionSetMapper.questionSetToQuestionSetResponse(item)).toList();

        return ApiStructResponse.<List<QuestionSetResponse>>builder()
                .message("list all question set by admin")
                .data(questionSetResponseList)
                .build();
    }
}
