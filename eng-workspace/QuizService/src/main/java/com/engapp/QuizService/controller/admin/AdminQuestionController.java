package com.engapp.QuizService.controller.admin;

import com.engapp.QuizService.dto.request.AnswerRequest;
import com.engapp.QuizService.dto.request.QuestionRequest;
import com.engapp.QuizService.dto.request.update.QuestionUpdateRequest;
import com.engapp.QuizService.dto.response.ApiStructResponse;
import com.engapp.QuizService.dto.response.QuestionResponse;
import com.engapp.QuizService.exception.ApplicationException;
import com.engapp.QuizService.exception.ErrorCode;
import com.engapp.QuizService.mapper.QuestionMapper;
import com.engapp.QuizService.pojo.Question;
import com.engapp.QuizService.pojo.QuestionSet;
import com.engapp.QuizService.service.QuestionService;
import com.engapp.QuizService.service.QuestionSetService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value="/admin/question")
@Slf4j
public class AdminQuestionController {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionSetService questionSetService;

    @PostMapping(value="/create/{questionSetId}")
    public ApiStructResponse<QuestionResponse> create(@PathVariable(value="questionSetId") int questionSetId, @RequestBody QuestionRequest questionRequest) {
        Question question = this.questionService.createQuestion(questionSetId, questionRequest);
        QuestionResponse questionResponse = this.questionMapper.questionToQuestionResponse(question);

        return ApiStructResponse.<QuestionResponse>builder()
                .message("create question from admin")
                .data(questionResponse)
                .build();
    }

    @PostMapping(value="/create-multiple-question/{questionSetId}")
    public ApiStructResponse<List<QuestionResponse>> createMultiple(
            @PathVariable(value="questionSetId") Integer questionSetId
            ,@RequestPart(value = "file") MultipartFile file) {
        List<QuestionRequest> questionRequests = new ArrayList<>();
        try{
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            XSSFSheet sheet = workbook.getSheetAt(0);

            for(int i = 1; i<sheet.getPhysicalNumberOfRows(); i++){
                QuestionRequest questionRequest = new QuestionRequest();
                XSSFRow row = sheet.getRow(i);

                Set<AnswerRequest> answerRequests = new HashSet<>();

                questionRequest.setQuestionSetIdRequest(questionSetId);
                questionRequest.setQuestionNumber((int) row.getCell(0).getNumericCellValue());
                questionRequest.setQuestionContent(row.getCell(1).getStringCellValue());
                questionRequest.setExplainAnswer(row.getCell(2).getStringCellValue());
                questionRequest.setCorrectAnswer((row.getCell(3).getStringCellValue()).toUpperCase());

                answerRequests.add(new AnswerRequest(row.getCell(4).getStringCellValue(), "A"));
                answerRequests.add(new AnswerRequest(row.getCell(5).getStringCellValue(), "B"));
                answerRequests.add(new AnswerRequest(row.getCell(6).getStringCellValue(), "C"));
                answerRequests.add(new AnswerRequest(row.getCell(7).getStringCellValue(), "D"));

                questionRequest.setAnswerSet(answerRequests);

                questionRequests.add(questionRequest);
            }
        }
        catch(Exception e){
            throw new ApplicationException(ErrorCode.RUNTIME_EXCEPTION);
        }

        List<Question> questions = this.questionService.createMultipleQuestions(questionSetId, questionRequests);

        List<QuestionResponse> questionResponseList = questions
                .stream()
                .map(item -> this.questionMapper.questionToQuestionResponse(item))
                .toList();

        return ApiStructResponse.<List<QuestionResponse>>builder()
                .message("create multiple question from admin")
                .data(questionResponseList)
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

    @KafkaListener(topics="cancel-save-questions", groupId = "delete-event-group", containerFactory = "kafkaListenerCancelSaveQuestionsContainerFactory")
    public void cancelSaveQuestions(Integer questionSetId) {
        this.questionService.deleteMultipleQuestions(questionSetId);
        log.info("Deleted list of question by questionSetId !");
    }
}
