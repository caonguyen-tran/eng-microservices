package com.engapp.QuizService.dto.request;

import com.engapp.QuizService.pojo.QuestionSet;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
public class QuestionRequest {
    private int questionSetIdRequest;
    private Integer questionNumber;
    private String questionContent;
    private String explainAnswer;
    private QuestionSet questionSet;
}
