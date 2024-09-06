package com.engapp.WordService.dto.request;

import com.engapp.WordService.utils.PofSpeech;
import com.engapp.WordService.utils.WordLevel;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WordUpdateRequest {
    String id;
    String word;
    PofSpeech pofSpeech;
    String pronunciation;
    String definition;
    String example;
    WordLevel wordLevel;
}
