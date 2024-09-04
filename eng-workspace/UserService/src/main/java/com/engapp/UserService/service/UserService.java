package com.engapp.UserService.service;

import com.engapp.UserService.dto.request.PutPasswordRequest;
import com.engapp.UserService.dto.request.SecureUserRequest;
import com.engapp.UserService.dto.request.UserRequest;
import com.engapp.UserService.dto.response.ApiStructResponse;
import com.engapp.UserService.dto.response.UserResponse;
import com.engapp.UserService.pojo.User;

import java.util.List;

public interface UserService {

    public User getUserByUsername(String username);

    public User userRegister(UserRequest userRequest);

    public String getPasswordHashFromSecurityService(String password);

    public List<UserResponse> getUserList();

    public User getUserByUsernameWithTrustKey(SecureUserRequest secureUserRequest);

    public User getInfo();

    public String deleteUserById(String userId);

    public User updatePassword(PutPasswordRequest putPasswordRequest);

    public User getUserById(String userId);
}
