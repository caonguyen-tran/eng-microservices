package com.engapp.QuizService.utils.enumerate;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
public enum PointEnum {
        READING_POINT("Reading part", 5),
    ;

    String name;
    int point;

    PointEnum(String name, int point){
        this.name = name;
        this.point = point;
    }
}
