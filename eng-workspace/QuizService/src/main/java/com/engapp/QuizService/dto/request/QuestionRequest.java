package com.engapp.QuizService.dto.request;

import com.engapp.QuizService.pojo.QuestionSet;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;


@Data
@Getter
@Setter
public class QuestionRequest {
    private int questionSetIdRequest;
    private Integer questionNumber;
    private String questionContent;
    private String explainAnswer;
    private Set<AnswerRequest> answerSet;
    private QuestionSet questionSet;
    private String correctAnswer;
}
