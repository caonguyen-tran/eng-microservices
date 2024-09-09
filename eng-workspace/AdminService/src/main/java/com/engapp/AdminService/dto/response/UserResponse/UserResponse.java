package com.engapp.AdminService.dto.response.UserResponse;

import com.engapp.AdminService.dto.clone.UserClone.RoleClone;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String id;

    private String username;

    private String email;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private Set<RoleClone> roles;
}
