package com.engapp.UserService.repository.httpClient;

import com.engapp.UserService.configuration.RequestInterceptorConfiguration;
import com.engapp.UserService.dto.request.PutPasswordRequest;
import com.engapp.UserService.dto.response.ApiStructResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "security-service", url = "${app.services.profile}", configuration = {RequestInterceptorConfiguration.class})
public interface SecurityClient {
    @PostMapping(value="/internal/auth/get-hashing-password", produces = MediaType.APPLICATION_JSON_VALUE)
    ApiStructResponse<String> getHashingPassword(@RequestBody String password);

    @PostMapping(value="/auth/matching-password", produces = MediaType.APPLICATION_JSON_VALUE)
    ApiStructResponse<Boolean> matchingPassword(@RequestBody PutPasswordRequest putPasswordRequest);
}
