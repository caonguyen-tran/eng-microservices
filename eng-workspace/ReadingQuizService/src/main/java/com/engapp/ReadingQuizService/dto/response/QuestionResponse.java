package com.engapp.ReadingQuizService.dto.response;

import com.engapp.ReadingQuizService.pojo.Answer;
import com.engapp.ReadingQuizService.pojo.QuestionSet;
import lombok.*;

import java.time.Instant;
import java.util.List;
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
