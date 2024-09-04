package com.engapp.SecurityService.controller;

import com.engapp.SecurityService.dto.reponse.ApiStructResponse;
import com.engapp.SecurityService.dto.reponse.IntrospectResponse;
import com.engapp.SecurityService.dto.request.AuthenticationRequest;
import com.engapp.SecurityService.dto.request.IntrospectRequest;
import com.engapp.SecurityService.dto.request.PutPasswordRequest;
import com.engapp.SecurityService.service.AuthenticationService;
import com.engapp.SecurityService.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@Slf4j
public class SecurityController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

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

    @GetMapping("/info")
    public ApiStructResponse<Set<String>> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        log.info("Current user: {}", authentication.getName());
        log.info("Current user authorities: {}", authorities);
        Set<String> setAuthority = authorities.stream().map(SimpleGrantedAuthority::getAuthority).collect(Collectors.toSet());
        return ApiStructResponse.<Set<String>>builder()
                .message("Principal authority")
                .data(setAuthority)
                .build();
    }

    @PostMapping("/matching-password")
    public ApiStructResponse<Boolean> matchingPassword(@RequestBody PutPasswordRequest putPasswordRequest) {
        boolean isMatching = this.userService.matchingPassword(putPasswordRequest);
        return ApiStructResponse.<Boolean>builder()
                .code(2034)
                .message("Matching password")
                .data(isMatching)
                .build();
    }
}
