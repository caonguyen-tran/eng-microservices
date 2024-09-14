package com.engapp.AdminService.dto.clone.QuizClone;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class Answer {
    private Integer id;
    private Question question;
    private String content;
    private String answerKey;
    private Instant createdDate;
}