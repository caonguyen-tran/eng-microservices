package com.engapp.ReadingQuizService.pojo;

import com.engapp.ReadingQuizService.utils.ReadingPart;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;

@Getter
@Setter
@Builder
@Document(value="question-set")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class QuestionSet {
    @MongoId
    String id;
    String name;
    String description;
    Instant createdAt;
    Instant updatedAt;
    ReadingPart readingPart;
    int yearOf;
}
