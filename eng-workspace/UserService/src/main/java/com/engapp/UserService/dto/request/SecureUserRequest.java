package com.engapp.UserService.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SecureUserRequest {
    private String username;
    private String trustKey;
}
