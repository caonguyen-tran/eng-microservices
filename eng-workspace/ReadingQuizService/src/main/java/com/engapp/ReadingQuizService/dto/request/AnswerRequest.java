package com.engapp.ReadingQuizService.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class AnswerRequest {
    private String content;
    private boolean isResult;
}
