package com.engapp.ReadingQuizService.dto.request;

import com.engapp.ReadingQuizService.pojo.Question;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class AnswerRequest {
    private int questionIdRequest;
    private String content;
    private Boolean isResult;
    private Question question;
}
