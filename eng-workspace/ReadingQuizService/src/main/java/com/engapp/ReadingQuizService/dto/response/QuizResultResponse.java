package com.engapp.ReadingQuizService.dto.response;

import com.engapp.ReadingQuizService.pojo.Question;
import lombok.*;

import java.time.Instant;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizResultResponse {
    private String takeBy;
    private QuestionSetResponse questionSet;
    private Instant startTime;
    private Instant endTime;
    private int correctAnswers;
    private double correctPercentage;
    private int overallPoints;
}
