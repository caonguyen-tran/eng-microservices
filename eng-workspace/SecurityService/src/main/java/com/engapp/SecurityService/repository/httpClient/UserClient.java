package com.engapp.SecurityService.repository.httpClient;

import com.engapp.SecurityService.dto.clone.UserClone;
import com.engapp.SecurityService.dto.reponse.ApiStructResponse;
import com.engapp.SecurityService.dto.request.SecureUserRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="user-service", url = "http://localhost:8080/user-service")
public interface UserClient {
    @PostMapping(value="/user/internal/get-by-username")
    ApiStructResponse<UserClone> getUserByUsername(@RequestBody SecureUserRequest secureUserRequest);
}
