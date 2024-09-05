package com.engapp.WordService.feign;

import com.engapp.WordService.configuration.RequestInterceptorConfiguration;
import com.engapp.WordService.dto.response.ApiStructResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${app.services.collection-service.name}", path = "${app.services.collection-service.context-path}", configuration = {RequestInterceptorConfiguration.class})
public interface CollectionClient {

    @GetMapping(value="/internal/collection/inspect-owner")
    public ApiStructResponse<Boolean> inspectOwner(@RequestParam("collectionId") String collectionId);
}
