package com.engapp.SecurityService.dto.clone;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserClone {
    String username;
    String email;
    String password;
    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private Set<RoleClone> roles;
}