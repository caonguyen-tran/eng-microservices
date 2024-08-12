package com.engapp.SecurityService.service;

import com.engapp.SecurityService.dto.reponse.UserResponse;
import com.engapp.SecurityService.dto.request.UserRequest;

public interface UserService {

    public UserResponse createUser(UserRequest userRequest);
}
