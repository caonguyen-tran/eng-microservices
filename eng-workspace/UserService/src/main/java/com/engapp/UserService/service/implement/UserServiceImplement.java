package com.engapp.UserService.service.implement;

import com.engapp.UserService.constant.KeySecure;
import com.engapp.UserService.dto.request.PutPasswordRequest;
import com.engapp.UserService.dto.request.SecureUserRequest;
import com.engapp.UserService.dto.request.UserRequest;
import com.engapp.UserService.dto.request.admin.UpdatePasswordRequest;
import com.engapp.UserService.dto.response.UserResponse;
import com.engapp.UserService.exception.ApplicationException;
import com.engapp.UserService.exception.ErrorCode;
import com.engapp.UserService.mapper.UserMapper;
import com.engapp.UserService.pojo.Role;
import com.engapp.UserService.pojo.User;
import com.engapp.UserService.repository.UserRepository;
import com.engapp.UserService.feign.SecurityClient;
import com.engapp.UserService.service.RoleService;
import com.engapp.UserService.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Slf4j
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
    public User userRegister(UserRequest userRequest) {
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
        this.userRepository.save(user);
        return user;
    }

    @Override
    public String getPasswordHashFromSecurityService(String password) {
        return this.securityClient.getHashingPassword(password).getData();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
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

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @Override
    public User getInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return this.userRepository.findByUsername(username).orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public String deleteUserById(String userId) {
        User user = this.getUserById(userId);
        this.userRepository.delete(user);
        return "Delete user by username successfully";
    }

    @PreAuthorize("hasAuthority('USER')")
    @Override
    public User updatePassword(PutPasswordRequest putPasswordRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        putPasswordRequest.setUsername(username);
        boolean isMatch = checkMatchPassword(putPasswordRequest);
        if(!isMatch){
            throw new ApplicationException(ErrorCode.WRONG_PASSWORD);
        }
        User user = this.getUserByUsername(username);
        String newPasswordHash = this.securityClient.getHashingPassword(putPasswordRequest.getNewPassword()).getData();
        user.setPassword(newPasswordHash);
        user.setUpdatedDate(LocalDateTime.now());
        this.userRepository.save(user);
        return user;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public User getUserById(String userId) {
        return this.userRepository.findById(userId).orElseThrow(
                () -> new ApplicationException(ErrorCode.USER_NOT_FOUND)
        );
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public User updatePasswordByAdmin(UpdatePasswordRequest updatePasswordRequest) {
        User user = this.getUserById(updatePasswordRequest.getUserId());
        String username = user.getUsername();
        PutPasswordRequest putPasswordRequest = new PutPasswordRequest();
        putPasswordRequest.setNewPassword(updatePasswordRequest.getNewPassword());
        putPasswordRequest.setOldPassword(updatePasswordRequest.getOldPassword());
        putPasswordRequest.setUsername(username);
        boolean isMatch = checkMatchPassword(putPasswordRequest);
        if(!isMatch){
            throw new ApplicationException(ErrorCode.WRONG_PASSWORD);
        }
        String newPasswordHash = this.securityClient.getHashingPassword(putPasswordRequest.getNewPassword()).getData();
        user.setPassword(newPasswordHash);
        user.setUpdatedDate(LocalDateTime.now());
        this.userRepository.save(user);
        return user;
    }

    @Override
    public Set<Role> getRoleListByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = this.getUserByUsername(authentication.getName());
        return user.getRoles();
    }

    public boolean checkMatchPassword(PutPasswordRequest putPasswordRequest) {
        return this.securityClient.matchingPassword(putPasswordRequest).getData();
    }
}
