package com.engapp.QuizService.dto.response;

import com.engapp.QuizService.pojo.Answer;
import com.engapp.QuizService.pojo.QuestionSet;
import lombok.*;

import java.time.Instant;
import java.util.Set;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionResponse {
    private Integer questionNumber;
    private String questionContent;
    private QuestionSet questionSet;
    private Instant createdDate;
    private Instant updatedDate;
    private Set<Answer> answers;
    private String explainAnswer;
}
