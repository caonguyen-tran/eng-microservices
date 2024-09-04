package com.engapp.CollectionService.configuration;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class PrincipalConfiguration {
    private Authentication authentication;

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public CustomUserDetails getCustomUserDetails() {
        return (CustomUserDetails) this.getAuthentication().getPrincipal();
    }
}
