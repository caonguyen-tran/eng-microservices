package com.engapp.WordService.dto.response;

import com.engapp.WordService.utils.WordLevel;
import lombok.*;

import java.time.Instant;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WordResponse {
    String id;
    String word;
    String pronunciation;
    String definition;
    String example;
    Instant createdDate;
    Instant updatedDate;
    WordLevel wordLevel;
    String createdBy;
    String collectionId;
}
