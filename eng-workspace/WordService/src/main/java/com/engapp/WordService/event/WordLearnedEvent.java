package com.engapp.WordService.event;

import com.engapp.WordService.dto.response.WordResponse;
import com.engapp.WordService.utils.LearnedMaster;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class WordLearnedEvent {
    String id;
    String learnBy;
    Instant learnDate;
    Instant dueDate;
    LearnedMaster learnedMaster;
    boolean isReview;
    boolean isLearn;
    double successRate;
}
