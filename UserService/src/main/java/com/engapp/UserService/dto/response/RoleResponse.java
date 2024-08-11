package com.engapp.UserService.dto.response;

import com.engapp.UserService.pojo.Permission;
import lombok.*;

import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponse {
    private int id;

    private String name;

    private String description;

    private Set<Permission> permissions;
}
