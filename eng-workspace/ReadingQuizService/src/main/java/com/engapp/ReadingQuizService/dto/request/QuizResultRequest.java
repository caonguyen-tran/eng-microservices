package com.engapp.ReadingQuizService.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class QuizResultRequest {
    private String takeBy;
    private String questionSetId;
    private int correctAnswer;
    private double correctPercentage;
    private int overallPoints;
}
