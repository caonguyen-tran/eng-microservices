package com.engapp.ReadingQuizService.dto.response;

import com.engapp.ReadingQuizService.pojo.Answer;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionResponse {
    private int questionNumber;
    private String questionContent;
    private String questionSetId;
    private Instant createdAt;
    private Instant updatedAt;
    private List<Answer> answers;
    private String explainAnswer;
}
