package com.engapp.QuizService.dto.request.update;

import com.engapp.QuizService.utils.ReadingPart;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class QuestionSetUpdateRequest {
    private int id;
    private String nameUpdate;
    private String descriptionUpdate;
    private Integer readingPart;
    private Integer yearOfUpdate;
}
