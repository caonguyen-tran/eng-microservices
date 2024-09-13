package com.engapp.QuizService.dto.response;

import com.engapp.QuizService.pojo.Question;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExamResponsesExerciseResponse {
    private Integer id;
    private Question question;
}
