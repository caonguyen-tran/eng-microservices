package com.engapp.SecurityService.repository.httpClient;

import com.engapp.SecurityService.dto.clone.UserClone;
import com.engapp.SecurityService.dto.reponse.ApiStructResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="user-service", url = "http://localhost:8080/user-service")
public interface UserClient {
    @GetMapping(value="/user/get-by-username")
    ApiStructResponse<UserClone> getUserByUsername(@RequestParam String username);
}
