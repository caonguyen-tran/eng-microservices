package com.engapp.WordService.utils;

import com.engapp.WordService.deserializer.WordLevelDeserializer;
import com.engapp.WordService.exception.ApplicationException;
import com.engapp.WordService.exception.ErrorCode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Data
@JsonDeserialize(using = WordLevelDeserializer.class)
public class WordLevel {
    String level;
    String description;

    WordLevel(String level, String description) {
        this.level = level;
        this.description = description;
    }

    public static WordLevel getWordLevel(String level) {
        for (WordLevelEnum wordLevelEnum : WordLevelEnum.values()) {
            String enumLevel = wordLevelEnum.getLevel();
            if (enumLevel.equals(level)) {
                String description = wordLevelEnum.getDescription();
                return new WordLevel(enumLevel, description);
            }
        }
        throw new ApplicationException(ErrorCode.NOT_EXIST);
    }
}
