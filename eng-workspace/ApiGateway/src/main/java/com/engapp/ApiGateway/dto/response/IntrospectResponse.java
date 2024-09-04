package com.engapp.ApiGateway.dto.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IntrospectResponse {
    private String token;
    private boolean isValid;
}
