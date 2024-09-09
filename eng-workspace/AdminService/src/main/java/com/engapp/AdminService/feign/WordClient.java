package com.engapp.AdminService.feign;

import com.engapp.AdminService.configuration.RequestInterceptorConfiguration;
import com.engapp.AdminService.dto.response.ApiStructResponse;
import com.engapp.AdminService.dto.response.WordResponse.WordLearnedResponse;
import com.engapp.AdminService.dto.response.WordResponse.WordResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "${app.admin.services.word-service.name}", path = "${app.admin.services.word-service.context-path}", configuration = {RequestInterceptorConfiguration.class})
public interface WordClient {
    @GetMapping(value="/admin/word/list")
    ApiStructResponse<List<WordResponse>> listWord();

    @GetMapping(value="/admin/learned-word/list")
    ApiStructResponse<List<WordLearnedResponse>> listLearnedWord();
}
