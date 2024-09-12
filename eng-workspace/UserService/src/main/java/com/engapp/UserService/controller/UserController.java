package com.engapp.UserService.controller;

import com.engapp.UserService.dto.request.PutPasswordRequest;
import com.engapp.UserService.dto.request.UserRequest;
import com.engapp.UserService.dto.response.ApiStructResponse;
import com.engapp.UserService.dto.response.UserResponse;
import com.engapp.UserService.mapper.UserMapper;
import com.engapp.UserService.pojo.Role;
import com.engapp.UserService.pojo.User;
import com.engapp.UserService.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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
        User user = userService.userRegister(userRequest);
        UserResponse userResponse = this.userMapper.userToUserResponse(user);
        return new ApiStructResponse<>(2000, "Create successfully !", userResponse);
    }


    @GetMapping("/information")
    public ApiStructResponse<UserResponse> getUserInformation() {
        User user = this.userService.getInfo();
        UserResponse userResponse = this.userMapper.userToUserResponse(user);
        return ApiStructResponse.<UserResponse>builder()
                .code(1041)
                .message("User information is " + user.getUsername())
                .data(userResponse)
                .build();
    }

    @PutMapping("/update-password")
    public ApiStructResponse<UserResponse> putUser(@RequestBody @Valid PutPasswordRequest putPasswordRequest) {
        User user = this.userService.updatePassword(putPasswordRequest);
        UserResponse userResponse = this.userMapper.userToUserResponse(user);
        return ApiStructResponse.<UserResponse>builder()
                .code(2000)
                .message("Password updated successfully !")
                .data(userResponse)
                .build();
    }

    @GetMapping("/get-roles")
    public ApiStructResponse<Set<Role>> getRoles() {
        Set<Role> roles = this.userService.getRoleListByUser();

        return ApiStructResponse.<Set<Role>>builder()
                .code(2000)
                .message("Get roles of user !")
                .data(roles)
                .build();
    }
}
