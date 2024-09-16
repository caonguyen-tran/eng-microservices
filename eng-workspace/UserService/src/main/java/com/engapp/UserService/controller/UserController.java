package com.engapp.UserService.controller;

import com.engapp.UserService.dto.request.AuthenticationRequest;
import com.engapp.UserService.dto.request.PutPasswordRequest;
import com.engapp.UserService.dto.request.UserRequest;
import com.engapp.UserService.dto.response.ApiStructResponse;
import com.engapp.UserService.dto.response.UserResponse;
import com.engapp.UserService.exception.ApplicationException;
import com.engapp.UserService.exception.ErrorCode;
import com.engapp.UserService.mapper.UserMapper;
import com.engapp.UserService.pojo.Role;
import com.engapp.UserService.pojo.User;
import com.engapp.UserService.service.UserService;
import com.engapp.UserService.service.implement.OAuth2UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OAuth2UserService oAuth2UserService;


    @PostMapping(value="/login")
    ApiStructResponse<String> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        String result = this.userService.getTokenFromSecurityClient(authenticationRequest);
        return new ApiStructResponse<>(2000, "Authentication result", result);
    }


    @PostMapping("/register-user")
    public ApiStructResponse<UserResponse> postUser(@RequestBody @Valid UserRequest userRequest) {
        User user = userService.userRegisterByLocal(userRequest);
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

    @GetMapping(value = "/get-roles")
    public ApiStructResponse<Set<Role>> getRoles() {
        Set<Role> roles = this.userService.getRoleListByUser();

        return ApiStructResponse.<Set<Role>>builder()
                .code(2000)
                .message("Get roles of user !")
                .data(roles)
                .build();
    }

    @GetMapping(value = "/token-oauth2")
    public ApiStructResponse<String> getOauth2Token(@AuthenticationPrincipal OAuth2User principal) {
        User user = this.oAuth2UserService.processOauth2User(principal);

        if(user == null){
            throw new ApplicationException(ErrorCode.RUNTIME_EXCEPTION);
        }

        UserResponse userResponse = this.userMapper.userToUserResponse(user);
        String token = this.userService.getTokenOAuth2FromSecurityClient(userResponse);

        return ApiStructResponse.<String>builder()
                .message("Oauth2 token from security client !")
                .data(token)
                .build();
    }

    @GetMapping(value="/authentication-fail")
    public String getAuthenticationFail() {
        return "Authentication fail";
    }
}
