package com.engapp.UserService.repository.httpClient;

import com.engapp.UserService.dto.response.ApiStructResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "security-service", url = "${app.services.profile}")
public interface SecurityClient {
    @PostMapping(value="/internal/auth/get-hashing-password", produces = MediaType.APPLICATION_JSON_VALUE)
    ApiStructResponse<String> getHashingPassword(@RequestBody String password);
}
