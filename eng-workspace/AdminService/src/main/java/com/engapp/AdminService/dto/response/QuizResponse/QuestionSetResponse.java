package com.engapp.AdminService.dto.response.QuizResponse;

import lombok.*;

import java.time.Instant;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionSetResponse {
    private Integer id;
    private String name;
    private String description;
    private Instant createdDate;
    private Instant updatedDate;
    private Integer readingPart;
    private Integer yearOf;
    private Boolean isActive;
}
