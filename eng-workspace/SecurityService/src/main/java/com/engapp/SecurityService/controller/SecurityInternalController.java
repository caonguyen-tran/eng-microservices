package com.engapp.SecurityService.controller;

import com.engapp.SecurityService.dto.clone.UserClone;
import com.engapp.SecurityService.dto.reponse.ApiStructResponse;
import com.engapp.SecurityService.dto.reponse.IntrospectResponse;
import com.engapp.SecurityService.dto.request.IntrospectRequest;
import com.engapp.SecurityService.dto.request.SecureUserRequest;
import com.engapp.SecurityService.service.AuthenticationService;
import com.engapp.SecurityService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal/auth")
public class SecurityInternalController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/get-hashing-password")
    public ApiStructResponse<String> getHashingPassword(@RequestBody String password) {
        String passwordHashing = this.userService.getPasswordHash(password);
        return new ApiStructResponse<>(2000, "Password hashing", passwordHashing);
    }

    @PostMapping("/get-user-by-username")
    public ApiStructResponse<UserClone> getUserByUsername(@RequestBody SecureUserRequest secureUserRequest) {
        UserClone userClone = this.userService.getUserByUsernameFromUserClient(secureUserRequest);
        return new ApiStructResponse<>(2000, "User clone", userClone);
    }
}
