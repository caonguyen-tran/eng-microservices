package com.engapp.AdminService.feign;

import com.engapp.AdminService.configuration.RequestInterceptorConfiguration;
import com.engapp.AdminService.dto.response.QuizResponse.QuestionResponse;
import com.engapp.AdminService.dto.response.QuizResponse.QuestionSetResponse;
import com.engapp.AdminService.dto.response.ApiStructResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient(name = "${app.admin.services.quiz-service.name}", path = "${app.admin.services.quiz-service.context-path}", configuration = {RequestInterceptorConfiguration.class})
public interface QuizClient {
    @GetMapping(value="/admin/question-set/get-all")
    ApiStructResponse<List<QuestionSetResponse>> getAllQuestionSets(
            @RequestParam(defaultValue = "0") Integer pageNo
            , @RequestParam(defaultValue = "10") Integer pageSize
            ,@RequestParam(defaultValue = "id") String sortBy);

    @GetMapping(value="/admin/question/get-all")
    ApiStructResponse<List<QuestionResponse>> getAllQuestion();

    @PostMapping(value="/admin/question/create-multiple-question")
    ApiStructResponse<List<QuestionResponse>> createMultipleQuestion(@RequestPart(value="file") MultipartFile file);
}
