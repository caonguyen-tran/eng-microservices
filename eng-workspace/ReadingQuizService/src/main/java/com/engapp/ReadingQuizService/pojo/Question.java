package com.engapp.ReadingQuizService.pojo;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
@Document(value="question-set")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @MongoId
    String id;
    String question;
    int questionNumber;
    Instant createdAt;
    Instant updatedAt;
    List<Answer> answers;
    String explainAnswer;
}
