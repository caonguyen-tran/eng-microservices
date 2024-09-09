package com.engapp.AdminService.dto.response.WordResponse;

import com.engapp.AdminService.utils.LearnedMaster;
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
    double successRate;
}
