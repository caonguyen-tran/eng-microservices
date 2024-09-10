package com.engapp.ReadingQuizService.pojo;

import com.engapp.ReadingQuizService.dto.response.QuestionResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Setter
@Getter
@Builder
@Document(value="exam-responses")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class ExamResponses {
    @MongoId
    String id;
    String takeBy;
    QuestionResponse questionResponse;
    Answer answer;
    boolean isCorrect;
}
