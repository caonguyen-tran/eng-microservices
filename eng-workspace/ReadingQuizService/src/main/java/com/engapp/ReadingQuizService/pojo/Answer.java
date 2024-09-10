package com.engapp.ReadingQuizService.pojo;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;

@Getter
@Setter
@Builder
@Document(value="answer")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    @MongoId
    String id;
    String content;
    String questionId;
    boolean isResult;
    Instant createdAt;
}
