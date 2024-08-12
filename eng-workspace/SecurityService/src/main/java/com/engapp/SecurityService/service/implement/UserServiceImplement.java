package com.engapp.SecurityService.service.implement;

import com.engapp.SecurityService.dto.reponse.UserResponse;
import com.engapp.SecurityService.dto.request.UserRequest;
import com.engapp.SecurityService.mapper.UserMapper;
import com.engapp.SecurityService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplement implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserResponse createUser(UserRequest userRequest) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userRequest.getPassword());
        userRequest.setPassword(encodedPassword);
        return this.userMapper.userRequestToUserResponse(userRequest);
    }
}
