package com.engapp.AdminService.dto.clone.QuizClone;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class QuestionSet {
    private Integer id;
    private String name;
    private String description;
    private Instant createdDate;
    private Instant updatedDate;
    private Integer readingPart;
    private Integer yearOf;

}