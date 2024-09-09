package com.engapp.WordService.dto.response;

import com.engapp.WordService.pojo.Word;
import com.engapp.WordService.utils.LearnedMaster;
import lombok.*;

import java.time.Instant;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WordLearnedResponse {
    private String id;
    private WordResponse wordResponse;
    private String learnBy;
    private Instant learnDate;
    private Instant dueDate;
    LearnedMaster learnedMaster;
    boolean isReview;
    boolean isLearn;
    double successRate;
}
