package com.engapp.UserService.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class PermissionRequest {
    @NotNull
    private String permissionName;
}
