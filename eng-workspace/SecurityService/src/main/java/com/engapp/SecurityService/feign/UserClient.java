package com.engapp.SecurityService.feign;

import com.engapp.SecurityService.dto.clone.UserClone;
import com.engapp.SecurityService.dto.reponse.ApiStructResponse;
import com.engapp.SecurityService.dto.request.SecureUserRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="user-service", path="/user-service")
public interface UserClient {
    @PostMapping(value="/internal/user/get-by-username")
    ApiStructResponse<UserClone> getUserByUsername(@RequestBody SecureUserRequest secureUserRequest);
}
