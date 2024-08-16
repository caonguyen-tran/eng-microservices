package com.engapp.SecurityService.dto.reponse;

import lombok.*;

@Setter
@Getter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IntrospectResponse {
    private String token;
    private boolean isValid;
}
