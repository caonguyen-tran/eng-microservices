package com.engapp.UserService.dto.request;

import com.engapp.UserService.pojo.Permission;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Data
public class RoleRequest {

    @NotNull(message = "VALUE_NOT_NULL")
    @Size(min = 2, max = 50)
    private String name;

    @NotNull(message = "VALUE_NOT_NULL")
    @Size(min = 10, max = 255)
    private String description;

    private Set<Permission> permissions;
}
