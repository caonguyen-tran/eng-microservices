package com.engapp.WordService.pojo;

import com.engapp.WordService.dto.response.WordResponse;
import com.engapp.WordService.utils.LearnedMaster;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;

@Document(value = "word-learned")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class WordLearned {
    @MongoId
    String id;
    WordResponse wordResponse;
    String learnBy;
    Instant learnDate;
    Instant dueDate;
    LearnedMaster learnedMaster;
    boolean isReview;
    boolean isLearn;
    double successRate;

    @Override
    public String toString() {
        return this.getId();
    }
}
