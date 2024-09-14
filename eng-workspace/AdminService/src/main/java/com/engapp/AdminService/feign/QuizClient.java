package com.engapp.AdminService.feign;

import com.engapp.AdminService.configuration.RequestInterceptorConfiguration;
import com.engapp.AdminService.dto.clone.QuizClone.QuestionSetResponseClone;
import com.engapp.AdminService.dto.response.ApiStructResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "${app.admin.services.quiz-service.name}", path = "${app.admin.services.quiz-service.context-path}", configuration = {RequestInterceptorConfiguration.class})
public interface QuizClient {
    @GetMapping(value="/admin/question-set/get-all")
    ApiStructResponse<List<QuestionSetResponseClone>> getAllQuestionSets(
            @RequestParam(defaultValue = "0") Integer pageNo
            , @RequestParam(defaultValue = "10") Integer pageSize
            ,@RequestParam(defaultValue = "id") String sortBy);
}
