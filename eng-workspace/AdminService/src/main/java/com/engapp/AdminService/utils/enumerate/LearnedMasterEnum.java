package com.engapp.AdminService.utils.enumerate;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
public enum LearnedMasterEnum {
        ZERO(0, "No Rank", 0),
        ONE(1, "Explorer", 1),
        TWO(2, "Novice", 8),
        THREE(3, "Apprentice", 16),
        FOUR(4, "Proficient", 24),
        FIVE(5, "Expert", 48),
        SIX(6, "Virtuoso", 72),
    ;

    int key;
    String name;
    int durationReminder;

    LearnedMasterEnum(int key, String name, int durationReminder) {
        this.key = key;
        this.name = name;
        this.durationReminder = durationReminder;
    }
}
