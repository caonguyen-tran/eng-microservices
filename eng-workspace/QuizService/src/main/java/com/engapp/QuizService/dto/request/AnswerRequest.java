package com.engapp.QuizService.dto.request;

import com.engapp.QuizService.pojo.Question;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class AnswerRequest {
    private int questionIdRequest;
    private String content;
    private String answerKey;
    private Question question;
}
