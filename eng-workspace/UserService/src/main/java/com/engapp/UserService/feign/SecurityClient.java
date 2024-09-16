package com.engapp.UserService.feign;

import com.engapp.UserService.configuration.RequestInterceptorConfiguration;
import com.engapp.UserService.dto.request.AuthenticationRequest;
import com.engapp.UserService.dto.request.PutPasswordRequest;
import com.engapp.UserService.dto.response.ApiStructResponse;
import com.engapp.UserService.dto.response.UserResponse;
import com.engapp.UserService.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "${app.services.security-service.name}", path = "${app.services.security-service.context-path}")
public interface SecurityClient {
    @PostMapping(value="/internal/auth/get-hashing-password", produces = MediaType.APPLICATION_JSON_VALUE)
    ApiStructResponse<String> getHashingPassword(@RequestBody String password);

    @PostMapping(value="/auth/matching-password", produces = MediaType.APPLICATION_JSON_VALUE)
    ApiStructResponse<Boolean> matchingPassword(@RequestBody PutPasswordRequest putPasswordRequest);

    @PostMapping(value="/internal/auth/token-local")
    ApiStructResponse<String> getToken(@RequestBody AuthenticationRequest authenticationRequest);

    @PostMapping(value="/internal/auth/token-oauth2")
    ApiStructResponse<String> getTokenOAuth2(@RequestBody UserResponse userResponse);
}
