package com.engapp.UserService.dto.request.admin;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePasswordRequest {
    private String userId;

    @Size(min = 6, message = "PASSWORD_INVALID")
    @NotNull(message = "PASSWORD_NOT_NULL")
    private String oldPassword;

    @Size(min = 6, message = "PASSWORD_INVALID")
    @NotNull(message = "PASSWORD_NOT_NULL")
    private String newPassword;
}
