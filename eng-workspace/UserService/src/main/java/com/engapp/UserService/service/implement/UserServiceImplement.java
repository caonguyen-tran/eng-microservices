package com.engapp.UserService.service.implement;

import com.engapp.UserService.constant.KeySecure;
import com.engapp.UserService.dto.request.SecureUserRequest;
import com.engapp.UserService.dto.request.UserRequest;
import com.engapp.UserService.dto.response.UserResponse;
import com.engapp.UserService.exception.ApplicationException;
import com.engapp.UserService.exception.ErrorCode;
import com.engapp.UserService.mapper.UserMapper;
import com.engapp.UserService.pojo.Role;
import com.engapp.UserService.pojo.User;
import com.engapp.UserService.repository.UserRepository;
import com.engapp.UserService.repository.httpClient.SecurityClient;
import com.engapp.UserService.service.RoleService;
import com.engapp.UserService.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class UserServiceImplement implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    SecurityClient securityClient;

    @Autowired
    RoleService roleService;

    @Override
    public User getUserByUsername(String username) {
        return this.userRepository.findByUsername(username).orElseThrow(
                () -> new ApplicationException(ErrorCode.USER_NOT_FOUND)
        );
    }

    @Override
    public UserResponse userRegister(UserRequest userRequest) {
        if(this.userRepository.findByUsername(userRequest.getUsername()).isPresent()) {
            throw new ApplicationException(ErrorCode.USER_EXISTS);
        }
        String passwordHash = getPasswordHashFromSecurityService(userRequest.getPassword());
        userRequest.setPassword(passwordHash);
        User user = this.userMapper.userRequestToUser(userRequest);
        HashSet<Role> roles = new HashSet<>();
        Role role = roleService.getRoleByName("USER");
        roles.add(role);
        user.setRoles(roles);
        return this.userMapper.userToUserResponse(this.userRepository.save(user));
    }

    @Override
    public String getPasswordHashFromSecurityService(String password) {
        return this.securityClient.getHashingPassword(password).getData();
    }

    @Override
    public List<UserResponse> getUserList() {
        List<User> users = this.userRepository.findAll();
        return users.stream().map(user -> this.userMapper.userToUserResponse(user)).toList();
    }

    @Override
    public User getUserByUsernameWithTrustKey(SecureUserRequest secureUserRequest) {
        if(!secureUserRequest.getTrustKey().equals(KeySecure.KEY_SECURE.getKey())){
            throw new ApplicationException(ErrorCode.TRUST_FAIL);
        }
        return getUserByUsername(secureUserRequest.getUsername());
    }
}
