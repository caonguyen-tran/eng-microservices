package com.engapp.WordService.utils;

import com.engapp.WordService.deserializer.WordLevelDeserializer;
import com.engapp.WordService.exception.ApplicationException;
import com.engapp.WordService.exception.ErrorCode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@JsonDeserialize(using = WordLevelDeserializer.class)
public enum WordLevel {
        A1("A1", "Beginner"),
        A2("A2", "Pre-intermediate"),
        B1("B1", "Intermediate"),
        B2("B2", "Upper-intermediate"),
        C1("C1", "Advanced"),
        C2("C2", "Mastery"),
    ;

    String level;
    String description;

    WordLevel(String level, String description) {
        this.level = level;
        this.description = description;
    }

    public static WordLevel getWordLevel(String level) {
        for (WordLevel wordLevel : WordLevel.values()) {
            if (wordLevel.level.equals(level)) {
                return wordLevel;
            }
        }
        throw new ApplicationException(ErrorCode.NOT_EXIST);
    }
}
