package com.engapp.UserService.dto.response;

import com.engapp.UserService.pojo.Role;
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

    private Set<Role> roles;
}
