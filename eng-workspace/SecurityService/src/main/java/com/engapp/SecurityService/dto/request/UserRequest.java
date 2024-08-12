package com.engapp.SecurityService.dto.request;

import com.engapp.SecurityService.dto.clone.RoleClone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String username;
    private String password;
    private String email;
    private Set<RoleClone> roles;
}
