package com.engapp.ReadingQuizService.dto.response;

import com.engapp.ReadingQuizService.utils.ReadingPart;
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
    private ReadingPart readingPartResponse;
    private Integer yearOf;
}
