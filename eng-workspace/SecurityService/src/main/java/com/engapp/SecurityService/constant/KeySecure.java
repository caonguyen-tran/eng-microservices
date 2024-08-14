package com.engapp.SecurityService.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum KeySecure {
    KEY_SECURE("ba5f76a057b521b8c2ab544f06587b092d1fe760917979dbabdbf2c4c048148d29461bc73f64c4bd29ff5bb79883b093614da15c86ffb08d15f5a8e512d4cfd2", "key secure services")
    ;

    private String key;

    private String description;
}
