package com.engapp.ReadingQuizService.utils;

import com.engapp.ReadingQuizService.deserializer.ReadingPartDeserializer;
import com.engapp.ReadingQuizService.exception.ApplicationException;
import com.engapp.ReadingQuizService.exception.ErrorCode;
import com.engapp.ReadingQuizService.utils.enumerate.ReadingPartEnum;
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
