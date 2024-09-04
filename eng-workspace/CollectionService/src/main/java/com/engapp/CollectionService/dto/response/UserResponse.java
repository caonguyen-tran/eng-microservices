package com.engapp.CollectionService.dto.response;

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
}
