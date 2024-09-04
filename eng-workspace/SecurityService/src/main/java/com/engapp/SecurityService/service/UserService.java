package com.engapp.SecurityService.service;

import com.engapp.SecurityService.dto.clone.UserClone;
import com.engapp.SecurityService.dto.request.PutPasswordRequest;
import com.engapp.SecurityService.dto.request.SecureUserRequest;

public interface UserService {

    public String getPasswordHash(String password);

    public UserClone getUserByUsernameFromUserClient(SecureUserRequest secureUserRequest);

    public boolean matchingPassword(PutPasswordRequest putPasswordRequest);
}
