package com.engapp.UserService.dto.response;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PermissionResponse {
    private int id;

    private String name;

    private String description;
}
