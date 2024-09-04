package com.engapp.ApiGateway.dto.request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IntrospectRequest {
    private String token;
}
