package com.engapp.UserService.controller;

import com.engapp.UserService.dto.request.UserRequest;
import com.engapp.UserService.dto.response.ApiStructResponse;
import com.engapp.UserService.dto.response.UserResponse;
import com.engapp.UserService.pojo.User;
import com.engapp.UserService.service.implement.UserServiceImplement;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserServiceImplement userServiceImplement;

    @RequestMapping("/")
    public String index() {
        User u = new User();
        u.setUsername("nguyen");
        u.setPassword("nguyen");

        log.info("hello");
        return String.format("Hello %s!", u.getUsername());
    }


    @PostMapping("/user")
    public ApiStructResponse<UserResponse> postUser(@RequestBody @Valid UserRequest userRequest) {
        UserResponse userResponse = userServiceImplement.userRegister(userRequest);
        return new ApiStructResponse<>(2000, "Create successfully !", userResponse);
    }
}
