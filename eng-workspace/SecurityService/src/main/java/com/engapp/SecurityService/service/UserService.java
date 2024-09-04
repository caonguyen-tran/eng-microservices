package com.engapp.SecurityService.service;

import com.engapp.SecurityService.dto.clone.UserClone;
import com.engapp.SecurityService.dto.reponse.UserResponse;
import com.engapp.SecurityService.dto.request.PasswordRequest;
import com.engapp.SecurityService.dto.request.SecureUserRequest;
import com.engapp.SecurityService.dto.request.UserRequest;

public interface UserService {

    public String getPasswordHash(String password);

    public UserClone getUserByUsernameFromUserClient(SecureUserRequest secureUserRequest);
}
