package com.engapp.AdminService.utils;

import com.engapp.AdminService.deserializer.LearnedMasterDeserializer;
import com.engapp.AdminService.exception.ApplicationException;
import com.engapp.AdminService.exception.ErrorCode;
import com.engapp.AdminService.utils.enumerate.LearnedMasterEnum;
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
