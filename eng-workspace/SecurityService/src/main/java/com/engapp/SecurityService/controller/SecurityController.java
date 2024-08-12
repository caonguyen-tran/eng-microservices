package com.engapp.SecurityService.controller;

import com.engapp.SecurityService.dto.clone.UserClone;
import com.engapp.SecurityService.dto.reponse.ApiStructResponse;
import com.engapp.SecurityService.dto.reponse.UserResponse;
import com.engapp.SecurityService.dto.request.PasswordRequest;
import com.engapp.SecurityService.dto.request.UserRequest;
import com.engapp.SecurityService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class SecurityController {

    @Autowired
    private UserService userService;

    @PostMapping("/get-hashing-password")
    public ApiStructResponse<String> getHashingPassword(@RequestBody String password) {
        String passwordHashing = this.userService.getPasswordHash(password);
        return new ApiStructResponse<>(2000, "Password hashing", passwordHashing);
    }

    @GetMapping("/get-user-by-username")
    public ApiStructResponse<UserClone> getUserByUsername(@RequestParam String username) {
        UserClone userClone = this.userService.getUserByUsernameFromUserClient(username);
        return new ApiStructResponse<>(2000, "User clone", userClone);
    }
}
