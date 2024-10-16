package com.engapp.QuizService.dto.response;

import com.engapp.QuizService.pojo.Answer;
import com.engapp.QuizService.pojo.Question;
import com.engapp.QuizService.pojo.QuizResult;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExamResponsesExerciseResponse {
    private Integer id;
    private Question question;
    private Answer answer;
    private QuizResult result;
}
