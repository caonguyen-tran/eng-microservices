package com.engapp.UserService.dto.response;

import lombok.*;

import java.time.LocalDateTime;

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
}
