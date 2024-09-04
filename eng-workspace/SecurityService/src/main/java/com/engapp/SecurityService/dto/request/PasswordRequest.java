package com.engapp.SecurityService.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PasswordRequest {
    @NotNull(message = "PASSWORD_NOT_NULL")
    private String password;
}
