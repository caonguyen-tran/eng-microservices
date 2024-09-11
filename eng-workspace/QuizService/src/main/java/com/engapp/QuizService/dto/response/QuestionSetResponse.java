package com.engapp.QuizService.dto.response;

import com.engapp.QuizService.utils.ReadingPart;
import lombok.*;

import java.time.Instant;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionSetResponse {
    private String name;
    private String description;
    private Instant createdDate;
    private Instant updatedDate;
    private Integer readingPart;
    private Integer yearOf;
}
