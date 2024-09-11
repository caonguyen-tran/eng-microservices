package com.engapp.QuizService.utils.enumerate;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
public enum ReadingPartEnum {
        PART5(5, "Part 5"),
        PART6(6, "Part 6"),
        PART7(7, "Part 7"),
    ;

    int key;
    String value;
    ReadingPartEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }
}
