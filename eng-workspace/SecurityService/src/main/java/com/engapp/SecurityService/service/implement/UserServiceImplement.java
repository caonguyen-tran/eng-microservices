package com.engapp.SecurityService.service.implement;

import com.engapp.SecurityService.constant.KeySecure;
import com.engapp.SecurityService.dto.clone.UserClone;
import com.engapp.SecurityService.dto.request.PutPasswordRequest;
import com.engapp.SecurityService.dto.request.SecureUserRequest;
import com.engapp.SecurityService.mapper.UserMapper;
import com.engapp.SecurityService.feign.UserClient;
import com.engapp.SecurityService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplement implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserClient userClient;

    @Override
    public String getPasswordHash(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public UserClone getUserByUsernameFromUserClient(SecureUserRequest secureUserRequest) {
        return this.userClient.getUserByUsername(secureUserRequest).getData();
    }

    @PreAuthorize("hasAuthority('USER')")
    @Override
    public boolean matchingPassword(PutPasswordRequest putPasswordRequest) {
        SecureUserRequest secureUserRequest = new SecureUserRequest(putPasswordRequest.getUsername(), KeySecure.KEY_SECURE.getKey());
        UserClone userClone = this.getUserByUsernameFromUserClient(secureUserRequest);
        return passwordEncoder.matches(putPasswordRequest.getOldPassword(), userClone.getPassword());
    }
}
