package com.engapp.UserService.service;

import com.engapp.UserService.pojo.User;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

public interface UserService {

    public User getUserByUsername(String username);

    public void userRegister(User user);
}
