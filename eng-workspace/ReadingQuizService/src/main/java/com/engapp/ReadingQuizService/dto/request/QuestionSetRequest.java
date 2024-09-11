package com.engapp.ReadingQuizService.dto.request;

import com.engapp.ReadingQuizService.utils.ReadingPart;
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
