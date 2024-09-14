package com.engapp.AdminService.dto.clone.QuizClone;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;

@Getter
@Setter
public class Question {
    private Integer id;
    private String questionContent;
    private QuestionSet questionSet;
    private Integer questionNumber;
    private Instant createdDate;
    private Instant updatedDate;
    private String explainAnswer;
    private Set<Answer> answers;
    private String correctAnswer;
}