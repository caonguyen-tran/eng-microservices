package com.engapp.UserService.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class RoleRequest {

    @NotNull
    @Size(min = 2, max = 50)
    private String roleName;

    @NotNull
    @Size(min = 10, max = 255)
    private String description;
}
