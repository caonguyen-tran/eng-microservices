package com.engapp.QuizService.controller;

import com.engapp.QuizService.dto.response.ApiStructResponse;
import com.engapp.QuizService.dto.response.ExamResponsesExerciseResponse;
import com.engapp.QuizService.dto.response.ExamResponsesResultResponse;
import com.engapp.QuizService.mapper.ExamResponsesMapper;
import com.engapp.QuizService.pojo.ExamResponses;
import com.engapp.QuizService.pojo.QuizResult;
import com.engapp.QuizService.service.ExamResponsesService;
import com.engapp.QuizService.service.QuizResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/exam-responses")
public class ExamResponsesController {
    @Autowired
    private ExamResponsesService examResponsesService;

    @Autowired
    private QuizResultService quizResultService;

    @Autowired
    private ExamResponsesMapper examResponsesMapper;

    @GetMapping(value="/exam-responses/get-result/{resultId}")
    public ApiStructResponse<List<ExamResponsesResultResponse>> getListExamResponsesResult(@PathVariable int resultId){
        QuizResult quizResult = this.quizResultService.findById(resultId);
        List<ExamResponses> examResponsesList = this.examResponsesService.getMultipleExamResponses(quizResult);

        List<ExamResponsesResultResponse> examResponsesResultResponseList = examResponsesList.stream()
                .map(item -> this.examResponsesMapper.examResponsesToExamResponsesResultResponse(item))
                .toList();

        return ApiStructResponse.<List<ExamResponsesResultResponse>>builder()
                .message("List exam responses result by quiz result id")
                .data(examResponsesResultResponseList)
                .build();
    }

    @GetMapping(value="/exam-responses/get-list-question/{resultId}")
    public ApiStructResponse<List<ExamResponsesExerciseResponse>> getListExamResponsesExercise(@PathVariable int resultId){
        QuizResult quizResult = this.quizResultService.findById(resultId);
        List<ExamResponses> examResponsesList = this.examResponsesService.getMultipleExamResponses(quizResult);

        List<ExamResponsesExerciseResponse> examResponsesExerciseResponseList = examResponsesList.stream()
                .map(item -> this.examResponsesMapper.examResponsesToExamResponsesExerciseResponse(item))
                .toList();

        return ApiStructResponse.<List<ExamResponsesExerciseResponse>>builder()
                .message("List exam responses question by quiz result id")
                .data(examResponsesExerciseResponseList)
                .build();
    }
}
