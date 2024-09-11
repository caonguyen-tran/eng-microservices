package com.engapp.ReadingQuizService.dto.request.update;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class AnswerUpdateRequest {
    private int id;
    private String contentUpdate;
    private Boolean isResult;
}
