package com.engapp.SecurityService.controller;

import com.engapp.SecurityService.dto.reponse.ApiStructResponse;
import com.engapp.SecurityService.dto.reponse.IntrospectResponse;
import com.engapp.SecurityService.dto.request.AuthenticationRequest;
import com.engapp.SecurityService.dto.request.IntrospectRequest;
import com.engapp.SecurityService.service.AuthenticationService;
import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class SecurityController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/token")
    ApiStructResponse<String> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        String result = authenticationService.authenticate(authenticationRequest);
        return new ApiStructResponse<>(2000, "Authentication result", result);
    }

    @PostMapping("/introspect")
    public ApiStructResponse<IntrospectResponse> introspect(@RequestHeader(value = "Authorization") IntrospectRequest introspectRequest) {
        IntrospectResponse introspectResponse = authenticationService.introspect(introspectRequest);

        return ApiStructResponse.<IntrospectResponse>builder()
                .code(1004)
                .message("Introspection result")
                .data(introspectResponse)
                .build();
    }
}
