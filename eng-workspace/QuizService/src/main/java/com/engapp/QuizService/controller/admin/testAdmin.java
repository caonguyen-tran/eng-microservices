package com.engapp.QuizService.controller.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/admin")
public class testAdmin {

    @GetMapping(value="/get")
    public String get() {
        return "Hello World";
    }
}
