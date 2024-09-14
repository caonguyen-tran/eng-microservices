package com.engapp.AdminService.dto.clone.QuizClone;

import lombok.*;

import java.time.Instant;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionSetResponseClone {
    private String name;
    private String description;
    private Instant createdDate;
    private Instant updatedDate;
    private Integer readingPart;
    private Integer yearOf;
}
