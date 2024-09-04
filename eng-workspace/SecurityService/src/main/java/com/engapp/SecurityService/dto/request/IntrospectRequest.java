package com.engapp.SecurityService.dto.request;

import lombok.*;

@Setter
@Getter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IntrospectRequest {
    private String token;
}
