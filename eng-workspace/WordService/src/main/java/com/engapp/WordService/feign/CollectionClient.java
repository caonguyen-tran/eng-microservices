package com.engapp.WordService.feign;

import com.engapp.WordService.configuration.RequestInterceptorConfiguration;
import com.engapp.WordService.dto.response.ApiStructResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@FeignClient(name = "${app.services.collection-service.name}", path = "${app.services.collection-service.context-path}", configuration = {RequestInterceptorConfiguration.class})
public interface CollectionClient {

    @GetMapping(value="/internal/collection/inspect-owner")
    ApiStructResponse<Boolean> inspectOwner(@RequestParam("collectionId") String collectionId);

    @GetMapping(value="/internal/download/inspect-download")
    ApiStructResponse<Boolean> inspectDownload(@RequestParam HashMap<String, String> param);
}
