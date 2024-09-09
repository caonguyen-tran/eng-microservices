package com.engapp.AdminService.dto.response.WordResponse;

import com.engapp.AdminService.utils.PofSpeech;
import com.engapp.AdminService.utils.WordLevel;
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
