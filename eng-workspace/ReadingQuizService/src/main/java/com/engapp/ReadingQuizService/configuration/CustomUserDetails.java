package com.engapp.ReadingQuizService.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@Setter
@Getter
@AllArgsConstructor
public class CustomUserDetails {
    private String id;
    private String username;
}
