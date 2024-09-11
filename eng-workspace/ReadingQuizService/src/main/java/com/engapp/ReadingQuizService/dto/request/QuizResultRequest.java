package com.engapp.ReadingQuizService.dto.request;

import com.engapp.ReadingQuizService.pojo.QuestionSet;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class QuizResultRequest {
    private String userId;
    private String questionSetIdRequest;
    private Integer correctAnswers;
    private Double correctPercentage;
    private Integer overallPoint;
    private QuestionSet questionSet;
}
