package com.engapp.ReadingQuizService.dto.request.update;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class AnswerUpdateRequest {
    private String id;
    private String contentUpdate;
    private boolean isResult;
}
