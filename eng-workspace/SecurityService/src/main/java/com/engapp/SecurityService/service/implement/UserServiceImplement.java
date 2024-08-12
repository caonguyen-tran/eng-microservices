package com.engapp.SecurityService.service.implement;

import com.engapp.SecurityService.dto.clone.UserClone;
import com.engapp.SecurityService.dto.reponse.UserResponse;
import com.engapp.SecurityService.dto.request.PasswordRequest;
import com.engapp.SecurityService.dto.request.UserRequest;
import com.engapp.SecurityService.mapper.UserMapper;
import com.engapp.SecurityService.repository.httpClient.UserClient;
import com.engapp.SecurityService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    public UserClone getUserByUsernameFromUserClient(String username) {
        return this.userClient.getUserByUsername(username).getData();
    }
}
