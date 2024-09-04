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
    private String id;
    private String username;
    private String email;
    private String password;
    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private Set<RoleClone> roles;
}