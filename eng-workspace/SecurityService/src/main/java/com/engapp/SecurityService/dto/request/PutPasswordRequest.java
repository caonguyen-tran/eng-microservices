package com.engapp.SecurityService.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PutPasswordRequest {
    //this username request to get password by username, should not put oldPassword of user on body request
    private String username;
    private String oldPassword;
    private String newPassword;
}
