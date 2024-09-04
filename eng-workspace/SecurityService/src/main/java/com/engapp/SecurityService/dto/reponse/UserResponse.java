package com.engapp.SecurityService.dto.reponse;

import com.engapp.SecurityService.dto.clone.RoleClone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    String username;
    String password;
    String email;
    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private Set<RoleClone> roles;
}
