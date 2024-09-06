package com.engapp.WordService.utils.enumerate;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
public enum WordLevelEnum {
        A1("A1", "Beginner"),
        A2("A2", "Pre-intermediate"),
        B1("B1", "Intermediate"),
        B2("B2", "Upper-intermediate"),
        C1("C1", "Advanced"),
        C2("C2", "Mastery"),
    ;

    String level;
    String description;

    WordLevelEnum(String level, String description) {
        this.level = level;
        this.description = description;
    }
}
