package com.engapp.AdminService.utils;

import com.engapp.AdminService.deserializer.PofSpeechDeserializer;
import com.engapp.AdminService.exception.ApplicationException;
import com.engapp.AdminService.exception.ErrorCode;
import com.engapp.AdminService.utils.enumerate.PofSpeechEnum;
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
@JsonDeserialize(using = PofSpeechDeserializer.class)
public class PofSpeech {

    String key;
    String engName;
    String viName;

    PofSpeech(String key, String engName, String viName){
        this.key = key;
        this.engName = engName;
        this.viName = viName;
    }

    public static PofSpeech getPofSpeech(String key) {
        for (PofSpeechEnum pofSpeechEnum: PofSpeechEnum.values()) {
            String keyEnum = pofSpeechEnum.getKey();
            if (keyEnum.equals(key)) {
                String engName = pofSpeechEnum.getEngName();
                String viName = pofSpeechEnum.getViName();
                return new PofSpeech(keyEnum, engName, viName);
            }
        }
        throw new ApplicationException(ErrorCode.NOT_EXIST);
    }
}
