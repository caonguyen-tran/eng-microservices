package com.engapp.AdminService.feign;

import com.engapp.AdminService.configuration.RequestInterceptorConfiguration;
import com.engapp.AdminService.dto.request.UserRequest.UpdatePasswordRequest;
import com.engapp.AdminService.dto.request.UserRequest.UserRequest;
import com.engapp.AdminService.dto.response.ApiStructResponse;
import com.engapp.AdminService.dto.response.UserResponse.UserResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "${app.admin.services.user-service.name}", path = "${app.admin.services.user-service.context-path}", configuration = {RequestInterceptorConfiguration.class})
public interface UserClient {

    @GetMapping("/admin/user/get-list")
    ApiStructResponse<List<UserResponse>> getUserList();

    @DeleteMapping("/admin/user/remove/{userId}")
    ApiStructResponse<String> removeUser(@PathVariable String userId);

    @PostMapping("/admin/user/register-user")
    ApiStructResponse<UserResponse> postUser(@RequestBody @Valid UserRequest userRequest);

    @PatchMapping(value = "/admin/user/update-password")
    ApiStructResponse<UserResponse> updatePassword(@RequestBody @Valid UpdatePasswordRequest updatePasswordRequest);
}
