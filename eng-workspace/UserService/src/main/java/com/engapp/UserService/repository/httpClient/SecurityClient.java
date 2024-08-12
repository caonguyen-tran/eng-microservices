package com.engapp.UserService.repository.httpClient;

import com.engapp.UserService.dto.request.UserRequest;
import com.engapp.UserService.dto.response.ApiStructResponse;
import com.engapp.UserService.dto.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "security-service", url = "${app.services.profile}")
public interface SecurityClient {
    @PostMapping(value="/auth/create-user", produces = MediaType.APPLICATION_JSON_VALUE)
    ApiStructResponse<UserResponse> createUser(@RequestBody UserRequest userRequest);
}
