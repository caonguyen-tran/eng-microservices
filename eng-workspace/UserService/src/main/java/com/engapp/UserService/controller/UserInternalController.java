package com.engapp.UserService.controller;

import com.engapp.UserService.dto.request.SecureUserRequest;
import com.engapp.UserService.dto.response.ApiStructResponse;
import com.engapp.UserService.dto.response.SecurityUserResponse;
import com.engapp.UserService.mapper.UserMapper;
import com.engapp.UserService.pojo.User;
import com.engapp.UserService.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/internal/user")
public class UserInternalController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/get-by-username")
    public ApiStructResponse<SecurityUserResponse> getUserByUsername(@RequestBody SecureUserRequest secureUserRequest) {
        User user = this.userService.getUserByUsernameWithTrustKey(secureUserRequest);
        SecurityUserResponse securityUserResponse = this.userMapper.userToSecurityUserResponse(user);
        return new ApiStructResponse<>(2000, "Get successfully !", securityUserResponse);
    }
}
