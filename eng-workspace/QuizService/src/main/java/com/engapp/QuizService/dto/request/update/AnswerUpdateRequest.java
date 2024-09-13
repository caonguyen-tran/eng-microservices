package com.engapp.QuizService.dto.request.update;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class AnswerUpdateRequest {
    private int id;
    private String contentUpdate;
    private String answerKeyUpdate;
}
