package com.engapp.QuizService.dto.response;

import com.engapp.QuizService.pojo.Answer;
import com.engapp.QuizService.pojo.Question;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExamResponsesResultResponse {
    private Integer id;
    private Answer answer;
    private Question question;
    private Boolean isCorrect;
    private Boolean isAnswer;
}
