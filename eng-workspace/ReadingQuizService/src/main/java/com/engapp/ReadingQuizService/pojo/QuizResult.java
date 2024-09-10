package com.engapp.ReadingQuizService.pojo;

import com.engapp.ReadingQuizService.dto.response.QuestionSetResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;

@Getter
@Setter
@Builder
@Document(value="quiz-result")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class QuizResult {
    @MongoId
    String id;
    String takeBy;
    QuestionSetResponse questionSet;
    Instant startTime;
    Instant endTime;
    Instant createAt;
    int correctAnswers;
    double correctPercentage;
    int overallPoints;
}
