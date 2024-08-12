package com.engapp.SecurityService.controller;

import com.engapp.SecurityService.dto.reponse.ApiStructResponse;
import com.engapp.SecurityService.dto.reponse.UserResponse;
import com.engapp.SecurityService.dto.request.UserRequest;
import com.engapp.SecurityService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class SecurityController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String testFunction(){
        return "Hello World";
    }

    @PostMapping("/create-user")
    public ApiStructResponse<UserResponse> createUser(@RequestBody UserRequest userRequest){
        UserResponse userResponse = this.userService.createUser(userRequest);
        return new ApiStructResponse<UserResponse>(2000, "created", userResponse);
    }
}
