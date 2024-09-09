package com.engapp.AdminService.utils;

import com.engapp.AdminService.deserializer.WordLevelDeserializer;
import com.engapp.AdminService.exception.ApplicationException;
import com.engapp.AdminService.exception.ErrorCode;
import com.engapp.AdminService.utils.enumerate.WordLevelEnum;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

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
