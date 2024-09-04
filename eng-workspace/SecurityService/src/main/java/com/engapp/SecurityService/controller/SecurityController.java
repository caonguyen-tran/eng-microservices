package com.engapp.SecurityService.controller;

import com.engapp.SecurityService.dto.reponse.ApiStructResponse;
import com.engapp.SecurityService.dto.reponse.IntrospectResponse;
import com.engapp.SecurityService.dto.request.AuthenticationRequest;
import com.engapp.SecurityService.dto.request.IntrospectRequest;
import com.engapp.SecurityService.service.AuthenticationService;
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
}
