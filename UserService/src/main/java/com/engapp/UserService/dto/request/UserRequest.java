package com.engapp.UserService.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UserRequest {
    @Size(min = 6)
    @NotNull
    private String username;

    @Size(min = 8)
    @NotNull
    private String password;

    @NotNull
    private String email;
}
