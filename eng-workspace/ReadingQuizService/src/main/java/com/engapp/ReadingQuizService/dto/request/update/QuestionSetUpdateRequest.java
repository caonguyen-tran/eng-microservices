package com.engapp.ReadingQuizService.dto.request.update;

import com.engapp.ReadingQuizService.utils.ReadingPart;
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
    private ReadingPart readingPart;
    private Integer yearOfUpdate;
}
