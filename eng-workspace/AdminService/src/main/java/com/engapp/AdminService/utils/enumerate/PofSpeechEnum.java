package com.engapp.AdminService.utils.enumerate;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
public enum PofSpeechEnum {
        NOUN("N", "Noun", "Danh từ"),
        PRONOUN("Pronoun", "Pronoun", "Đại từ"),
        VERB("V", "Verb", "Động từ"),
        ADVERB("Adv", "Adverb", "Trạng từ"),
        ADJECTIVE("Adj", "Adjective", "Tính từ"),
        PREPOSITION("Preposition", "Preposition", "Giới từ"),
        CONJUNCTION("Conjunction", "Conjunction", "Liên từ"),
        INTERJECTION("Interjection", "Interjection", "Thán từ"),
        PHRASAL_VERB("Phrasal Verb", "Phrasal verb", "Cụm động từ")
    ;
    String key;
    String engName;
    String viName;

    PofSpeechEnum(String key, String engName, String viName) {
        this.key = key;
        this.engName = engName;
        this.viName = viName;
    }
}
