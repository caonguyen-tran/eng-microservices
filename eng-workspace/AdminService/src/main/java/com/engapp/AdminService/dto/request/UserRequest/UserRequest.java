package com.engapp.AdminService.dto.request.UserRequest;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UserRequest {
    @Size(min = 6, message = "USERNAME_INVALID")
    @NotNull(message = "USERNAME_NOT_NULL")
    private String username;

    @Size(min = 6, message = "PASSWORD_INVALID")
    @NotNull(message = "PASSWORD_NOT_NULL")
    private String password;

    @Size(min = 6, message = "WRONG_VALUE")
    @NotNull(message = "VALUE_NOT_NULL")
    private String email;
}
