package com.engapp.UserService.controller;

import com.engapp.UserService.pojo.User;
import com.engapp.UserService.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ApplicationController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index() {
        User u = new User();
        u.setUsername("nguyen");
        u.setPassword("nguyen");

        log.info("hello");
        return String.format("Hello %s!", u.getUsername());
    }


    @PostMapping("/user")
    public void postUser(@RequestBody User user) {
        userService.userRegister(user);
    }
}
