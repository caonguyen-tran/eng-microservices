package com.engapp.QuizService.controller;

import com.engapp.QuizService.configuration.CustomUserDetails;
import com.engapp.QuizService.configuration.PrincipalConfiguration;
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

    @Autowired
    private PrincipalConfiguration principalConfiguration;

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
                .message("This question set was already taken.")
                .data(false)
                .build();
    }

    @PostMapping(value="/re-do-question-set/{questionSetId}")
    public ApiStructResponse<List<ExamResponses>> reDoQuestionSet(@PathVariable(value="questionSetId") Integer questionSetId){
        CustomUserDetails userDetails = this.principalConfiguration.getCustomUserDetails();
        QuizResult quizResult = this.quizResultService.getQuizResultByUserAndQuestionSet(userDetails.getId(), questionSetId);

        if(quizResult != null){
            List<ExamResponses> oldExamResponses = this.examResponsesService.getMultipleExamResponses(quizResult);
            List<ExamResponses> examResponsesList = this.examResponsesService.updateMultiplelExamResponses(oldExamResponses);

            return ApiStructResponse.<List<ExamResponses>>builder()
                    .message("Re do this question set and get exam responses!")
                    .data(examResponsesList)
                    .build();
        }

        return ApiStructResponse.<List<ExamResponses>>builder()
                .message("This quiz result of question set and user not found.")
                .data(null)
                .build();
    }

    @PostMapping(value="re-do-quiz-result/{resultId}")
    public ApiStructResponse<List<ExamResponses>> reDoQuizResult(@PathVariable(value="resultId") Integer resultId){
        QuizResult quizResult = this.quizResultService.findById(resultId);

        if(quizResult != null){
            List<ExamResponses> oldExamResponses = this.examResponsesService.getMultipleExamResponses(quizResult);
            List<ExamResponses> examResponsesList = this.examResponsesService.updateMultiplelExamResponses(oldExamResponses);

            return ApiStructResponse.<List<ExamResponses>>builder()
                    .message("Re do this question set and get exam responses!")
                    .data(examResponsesList)
                    .build();
        }

        return ApiStructResponse.<List<ExamResponses>>builder()
                .message("This quiz result of question set and user not found.")
                .data(null)
                .build();
    }
}
