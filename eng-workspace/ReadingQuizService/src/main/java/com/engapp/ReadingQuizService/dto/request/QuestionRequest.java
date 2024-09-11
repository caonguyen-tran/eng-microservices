package com.engapp.ReadingQuizService.dto.request;

import com.engapp.ReadingQuizService.pojo.QuestionSet;
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
