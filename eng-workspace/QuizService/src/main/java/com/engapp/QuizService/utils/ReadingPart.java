package com.engapp.QuizService.utils;

import com.engapp.QuizService.deserializer.ReadingPartDeserializer;
import com.engapp.QuizService.exception.ApplicationException;
import com.engapp.QuizService.exception.ErrorCode;
import com.engapp.QuizService.utils.enumerate.ReadingPartEnum;
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
@JsonDeserialize(using = ReadingPartDeserializer.class)
public class ReadingPart {

    int key;
    String value;

    ReadingPart(int key, String value){
        this.key = key;
        this.value = value;
    }

    public static ReadingPart getReadingPart(int key) {
        for (ReadingPartEnum readingPartEnum : ReadingPartEnum.values()) {
            int keyEnum = readingPartEnum.getKey();
            if (keyEnum == key) {
                String value = readingPartEnum.getValue();
                return new ReadingPart(keyEnum, value);
            }
        }
        throw new ApplicationException(ErrorCode.NOT_EXIST);
    }
}
