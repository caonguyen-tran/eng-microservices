package com.engapp.AdminService.dto.response.QuizResponse;

import com.engapp.AdminService.dto.clone.QuizClone.Answer;
import com.engapp.AdminService.dto.clone.QuizClone.QuestionSet;
import lombok.*;

import java.time.Instant;
import java.util.Set;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionResponse {
    private Integer id;
    private Integer questionNumber;
    private String questionContent;
    private QuestionSet questionSet;
    private Instant createdDate;
    private Instant updatedDate;
    private Set<Answer> answers;
    private String explainAnswer;
    private String correctAnswer;
}
