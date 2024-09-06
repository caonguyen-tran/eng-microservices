package com.engapp.WordService.pojo;

import com.engapp.WordService.utils.PofSpeech;
import com.engapp.WordService.utils.WordLevel;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;

@Document(value = "word")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Word {
    @MongoId
    String id;
    String word;
    PofSpeech pofSpeech;
    String pronunciation;
    String definition;
    String example;
    Instant createdDate;
    Instant updatedDate;
    WordLevel wordLevel;
    String createdBy;
    String collectionId;
}
