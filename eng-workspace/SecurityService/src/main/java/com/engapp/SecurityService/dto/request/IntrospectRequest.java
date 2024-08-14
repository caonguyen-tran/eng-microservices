package com.engapp.SecurityService.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IntrospectRequest {
    private String token;
}
