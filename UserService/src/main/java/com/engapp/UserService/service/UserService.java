package com.engapp.UserService.service;

import com.engapp.UserService.dto.request.UserRequest;
import com.engapp.UserService.dto.response.ApiStructResponse;
import com.engapp.UserService.dto.response.UserResponse;
import com.engapp.UserService.pojo.User;

public interface UserService {

    public User getUserByUsername(String username);

    public UserResponse userRegister(UserRequest userRequest);
}
