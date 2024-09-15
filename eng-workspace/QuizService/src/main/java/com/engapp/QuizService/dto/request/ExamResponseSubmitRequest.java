package com.engapp.QuizService.dto.request;

import com.engapp.QuizService.pojo.Answer;
import com.engapp.QuizService.pojo.Question;
import com.engapp.QuizService.pojo.QuizResult;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class ExamResponseSubmitRequest {
    private Integer id;
    private Question question;
    private Answer answer;
    private QuizResult result;
}
