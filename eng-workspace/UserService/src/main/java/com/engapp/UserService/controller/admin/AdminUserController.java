package com.engapp.UserService.controller.admin;

import com.engapp.UserService.dto.request.UserRequest;
import com.engapp.UserService.dto.request.admin.UpdatePasswordRequest;
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
@RequestMapping("/admin/user")
public class AdminUserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/get-list")
    public ApiStructResponse<List<UserResponse>> getUserList() {
        List<UserResponse> userList = this.userService.getUserList();
        for(UserResponse userResponse : userList) {
            System.out.println(userResponse.getUsername());
        }
        return new ApiStructResponse<>(2000, "Get list successfully !", userList);
    }

    @DeleteMapping("/remove/{userId}")
    public ApiStructResponse<String> removeUser(@PathVariable String userId) {
        String response = this.userService.deleteUserById(userId);
        return ApiStructResponse.<String>builder()
                .code(1010)
                .message("Remove user by user id.")
                .data(response)
                .build();
    }

    @PostMapping("/register-user")
    public ApiStructResponse<UserResponse> postUser(@RequestBody @Valid UserRequest userRequest) {
        User user = userService.userRegister(userRequest);
        UserResponse userResponse = this.userMapper.userToUserResponse(user);
        return new ApiStructResponse<>(2000, "Create successfully !", userResponse);
    }

    @PatchMapping(value = "/update-password")
    public ApiStructResponse<UserResponse> updatePassword(@RequestBody @Valid UpdatePasswordRequest updatePasswordRequest) {
        User user = this.userService.updatePasswordByAdmin(updatePasswordRequest);
        UserResponse userResponse = this.userMapper.userToUserResponse(user);
        return ApiStructResponse.<UserResponse>builder()
                .message("Update password successfully !")
                .data(userResponse)
                .build();
    }
}
