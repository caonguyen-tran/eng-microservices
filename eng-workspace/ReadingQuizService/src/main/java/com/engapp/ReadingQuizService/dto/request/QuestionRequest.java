package com.engapp.ReadingQuizService.dto.request;

import com.engapp.ReadingQuizService.pojo.Answer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

@Data
@Getter
@Setter
public class QuestionRequest {
    private int questionNumber;
    private String question;
    private List<AnswerRequest> answers;
    private String explainAnswer;
}
