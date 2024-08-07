package com.engapp.UserService.service.implement;

import com.engapp.UserService.pojo.User;
import com.engapp.UserService.repository.UserRepository;
import com.engapp.UserService.service.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceImplement implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void userRegister(User user) {
        userRepository.save(user);
    }
}
