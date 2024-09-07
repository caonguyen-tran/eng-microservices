package com.engapp.WordService.utils;

import com.engapp.WordService.deserializer.LearnedMasterDeserializer;
import com.engapp.WordService.deserializer.PofSpeechDeserializer;
import com.engapp.WordService.exception.ApplicationException;
import com.engapp.WordService.exception.ErrorCode;
import com.engapp.WordService.utils.enumerate.LearnedMasterEnum;
import com.engapp.WordService.utils.enumerate.PofSpeechEnum;
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
@JsonDeserialize(using = LearnedMasterDeserializer.class)
public class LearnedMaster {

    int key;
    String name;
    int durationReminder;

    LearnedMaster(int key, String name, int durationReminder){
        this.key = key;
        this.name = name;
        this.durationReminder = durationReminder;
    }

    public static LearnedMaster getLearnedMaster(int key) {
        for (LearnedMasterEnum learnedMasterEnum : LearnedMasterEnum.values()) {
            int keyEnum = learnedMasterEnum.getKey();
            if (keyEnum == key) {
                String name = learnedMasterEnum.getName();
                int durationReminder = learnedMasterEnum.getDurationReminder();
                return new LearnedMaster(keyEnum, name, durationReminder);
            }
        }
        throw new ApplicationException(ErrorCode.NOT_EXIST);
    }
}
