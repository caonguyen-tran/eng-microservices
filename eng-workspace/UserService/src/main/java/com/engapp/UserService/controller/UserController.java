package com.engapp.UserService.controller;

import com.engapp.UserService.dto.request.UserRequest;
import com.engapp.UserService.dto.response.ApiStructResponse;
import com.engapp.UserService.dto.response.UserResponse;
import com.engapp.UserService.mapper.UserMapper;
import com.engapp.UserService.pojo.User;
import com.engapp.UserService.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/")
    public String index() {
        User u = new User();
        u.setUsername("nguyen");
        u.setPassword("nguyen");

        log.info("hello");
        return String.format("Hello %s!", u.getUsername());
    }


    @PostMapping("/register-user")
    public ApiStructResponse<UserResponse> postUser(@RequestBody @Valid UserRequest userRequest) {
        UserResponse userResponse = userService.userRegister(userRequest);
        return new ApiStructResponse<>(2000, "Create successfully !", userResponse);
    }

    @GetMapping("/get-list")
    public ApiStructResponse<List<UserResponse>> getUserList() {
        List<UserResponse> userList = this.userService.getUserList();
        for(UserResponse userResponse : userList) {
            System.out.println(userResponse.getUsername());
        }
        return new ApiStructResponse<>(2000, "Get list successfully !", userList);
    }
}
