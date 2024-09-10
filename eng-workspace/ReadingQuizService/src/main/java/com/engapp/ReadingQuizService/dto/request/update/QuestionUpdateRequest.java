package com.engapp.ReadingQuizService.dto.request.update;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class QuestionUpdateRequest {
    private String id;
    private String questionContentUpdate;
    private int questionNumberUpdate;
    private String explainAnswerUpdate;
}
