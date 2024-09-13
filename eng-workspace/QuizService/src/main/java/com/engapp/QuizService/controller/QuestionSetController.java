package com.engapp.QuizService.controller;

import com.engapp.QuizService.dto.response.ApiStructResponse;
import com.engapp.QuizService.dto.response.QuestionSetResponse;
import com.engapp.QuizService.mapper.QuestionSetMapper;
import com.engapp.QuizService.pojo.ExamResponses;
import com.engapp.QuizService.pojo.Question;
import com.engapp.QuizService.pojo.QuestionSet;
import com.engapp.QuizService.pojo.QuizResult;
import com.engapp.QuizService.service.ExamResponsesService;
import com.engapp.QuizService.service.QuestionService;
import com.engapp.QuizService.service.QuestionSetService;
import com.engapp.QuizService.service.QuizResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/question-set")
public class QuestionSetController {
    @Autowired
    private QuestionSetService questionSetService;

    @Autowired
    private QuestionSetMapper questionSetMapper;

    @Autowired
    private QuizResultService quizResultService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ExamResponsesService examResponsesService;

    @GetMapping(value="/get-all")
    public ApiStructResponse<List<QuestionSetResponse>> getAllQuestionSet(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        List<QuestionSet> questionSetList = this.questionSetService.getQuestionSetByParams(pageNo, pageSize, sortBy);

        List<QuestionSetResponse> questionSetResponseList = questionSetList
                .stream()
                .map(item -> this.questionSetMapper.questionSetToQuestionSetResponse(item))
                .toList();
        return ApiStructResponse.<List<QuestionSetResponse>>builder()
                .message("List of question set by client.")
                .data(questionSetResponseList)
                .build();
    }


    @PostMapping(value="/do-question-set/{questionSetId}")
    public ApiStructResponse<Boolean> doQuestionSet(@PathVariable Integer questionSetId){
        QuizResult quizResult = new QuizResult();
        quizResult.setQuestionSet(this.questionSetService.getQuestionSetById(questionSetId));
        QuizResult quizResultCreate = this.quizResultService.createQuizResult(quizResult);

        if(quizResultCreate != null){
            List<Question> questionList = this.questionService.getByQuestionSetId(questionSetId);
            this.examResponsesService.createMultipleExamResponses(questionList, quizResult);

            return ApiStructResponse.<Boolean>builder()
                    .message("Do question set successfully.")
                    .data(true)
                    .build();
        }

        return ApiStructResponse.<Boolean>builder()
                .message("Quiz result already exist!")
                .data(false)
                .build();
    }
}
