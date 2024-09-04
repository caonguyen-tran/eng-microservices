package com.engapp.UserService.controller;

import com.engapp.UserService.dto.request.UserRequest;
import com.engapp.UserService.dto.response.ApiStructResponse;
import com.engapp.UserService.dto.response.UserResponse;
import com.engapp.UserService.mapper.UserMapper;
import com.engapp.UserService.pojo.User;
import com.engapp.UserService.service.UserService;
import com.engapp.UserService.service.implement.UserServiceImplement;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
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


    @PostMapping("/user")
    public ApiStructResponse<UserResponse> postUser(@RequestBody @Valid UserRequest userRequest) {
        System.out.println(userRequest.getUsername());
        UserResponse userResponse = userService.userRegister(userRequest);
        return new ApiStructResponse<>(2000, "Create successfully !", userResponse);
    }

    @GetMapping("/user/get-list")
    public ApiStructResponse<List<UserResponse>> getUserList() {
        List<UserResponse> userList = this.userService.getUserList();
        return new ApiStructResponse<>(2000, "Get list successfully !", userList);
    }

    @GetMapping("/user/get-by-username")
    public ApiStructResponse<UserResponse> getUserByUsername(@RequestParam String username) {
        User user = this.userService.getUserByUsername(username);
        UserResponse userResponse = this.userMapper.userToUserResponse(user);
        return new ApiStructResponse<>(2000, "Get successfully !", userResponse);
    }
}
