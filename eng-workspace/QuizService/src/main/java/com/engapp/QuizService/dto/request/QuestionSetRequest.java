package com.engapp.QuizService.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class QuestionSetRequest {
    private String name;
    private String description;
    private Integer readingPart;
    private Integer yearOf;
}
