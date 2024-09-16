package com.engapp.UserService.service.implement;

import com.engapp.UserService.pojo.Role;
import com.engapp.UserService.pojo.User;
import com.engapp.UserService.repository.UserRepository;
import com.engapp.UserService.service.RoleService;
import com.engapp.UserService.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;

@Slf4j
@Service
public class OAuth2UserService{
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    public User processOauth2User(OAuth2User principal){
        HashSet<Role> roles = new HashSet<>();
        Role role = roleService.getRoleByName("USER");
        roles.add(role);

        User user = new User();
        user.setUsername(principal.getAttribute("email"));
        user.setProvider("GOOGLE");
        user.setEmail(principal.getAttribute("email"));
        user.setPicture(principal.getAttribute("picture"));
        user.setCreatedDate(LocalDateTime.now());
        user.setUpdatedDate(LocalDateTime.now());
        user.setRoles(roles);
        user.setEnabled(true);
        user.setProviderId(principal.getAttribute("sub"));

        User userExisting = this.userService.getUserByUsername(user.getUsername());

        if(userExisting != null){
            return updateExistingUser(userExisting, user);
        }

        return this.userService.saveUserLoginByGoogle(user);
    }

    private User updateExistingUser(User existingUser, User user) {
        existingUser.setPicture(user.getPicture());
        existingUser.setUpdatedDate(LocalDateTime.now());
        return this.userService.saveUserLoginByGoogle(existingUser);
    }
}

