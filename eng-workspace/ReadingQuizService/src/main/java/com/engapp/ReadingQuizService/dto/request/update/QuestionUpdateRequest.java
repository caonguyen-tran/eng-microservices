package com.engapp.ReadingQuizService.dto.request.update;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class QuestionUpdateRequest {
    private int id;
    private String questionContentUpdate;
    private Integer questionNumberUpdate;
    private String explainAnswerUpdate;
}
