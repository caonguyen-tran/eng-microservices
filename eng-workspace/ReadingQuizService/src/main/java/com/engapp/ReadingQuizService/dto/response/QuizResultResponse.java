package com.engapp.ReadingQuizService.dto.response;

import lombok.*;

import java.time.Instant;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizResultResponse {
    private String userId;
    private QuestionSetResponse questionSetResponse;
    private Instant startTime;
    private Instant endTime;
    private Instant createdDate;
    private Integer correctAnswers;
    private Double correctPercentage;
    private Double overallPoint;
}
