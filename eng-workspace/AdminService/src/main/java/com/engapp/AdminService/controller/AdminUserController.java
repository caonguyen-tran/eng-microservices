package com.engapp.AdminService.controller;

import com.engapp.AdminService.dto.request.UserRequest.UpdatePasswordRequest;
import com.engapp.AdminService.dto.request.UserRequest.UserRequest;
import com.engapp.AdminService.dto.response.ApiStructResponse;
import com.engapp.AdminService.dto.response.UserResponse.UserResponse;
import com.engapp.AdminService.feign.UserClient;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/user")
public class AdminUserController {
    @Autowired
    private UserClient userClient;

    @GetMapping("/get-list")
    ApiStructResponse<List<UserResponse>> getUserList(){
        return userClient.getUserList();
    }

    @DeleteMapping("/remove/{userId}")
    ApiStructResponse<String> removeUser(@PathVariable String userId){
        return userClient.removeUser(userId);
    }

    @PostMapping("/register-user")
    ApiStructResponse<UserResponse> postUser(@RequestBody @Valid UserRequest userRequest){
        return this.userClient.postUser(userRequest);
    }

    @PatchMapping(value = "/update-password")
    ApiStructResponse<UserResponse> updatePassword(@RequestBody @Valid UpdatePasswordRequest updatePasswordRequest){
        return this.userClient.updatePassword(updatePasswordRequest);
    }
}
