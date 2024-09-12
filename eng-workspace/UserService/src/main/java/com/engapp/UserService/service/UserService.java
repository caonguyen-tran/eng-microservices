package com.engapp.UserService.service;

import com.engapp.UserService.dto.request.PutPasswordRequest;
import com.engapp.UserService.dto.request.SecureUserRequest;
import com.engapp.UserService.dto.request.UserRequest;
import com.engapp.UserService.dto.request.admin.UpdatePasswordRequest;
import com.engapp.UserService.dto.response.UserResponse;
import com.engapp.UserService.pojo.Role;
import com.engapp.UserService.pojo.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    User getUserByUsername(String username);

    User userRegister(UserRequest userRequest);

    String getPasswordHashFromSecurityService(String password);

    List<UserResponse> getUserList();

    User getUserByUsernameWithTrustKey(SecureUserRequest secureUserRequest);

    User getInfo();

    String deleteUserById(String userId);

    User updatePassword(PutPasswordRequest putPasswordRequest);

    User getUserById(String userId);

    User updatePasswordByAdmin(UpdatePasswordRequest updatePasswordRequest);

    Set<Role> getRoleListByUser();
}
