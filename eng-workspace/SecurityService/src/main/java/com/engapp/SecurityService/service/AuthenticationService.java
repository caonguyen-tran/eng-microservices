package com.engapp.SecurityService.service;

import com.engapp.SecurityService.constant.KeySecure;
import com.engapp.SecurityService.dto.clone.UserClone;
import com.engapp.SecurityService.dto.request.AuthenticationRequest;
import com.engapp.SecurityService.dto.request.SecureUserRequest;
import com.engapp.SecurityService.dto.request.UserRequest;
import com.engapp.SecurityService.exception.ApplicationException;
import com.engapp.SecurityService.exception.ErrorCode;
import com.engapp.SecurityService.repository.httpClient.UserClient;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
public class AuthenticationService {

    @NonFinal
    @Value("${jwt.signerkey}")
    String SIGNER_KEY;

    @NonFinal
    @Value("${jwt.valid-duration}")
    int VALIDATION_DURATION;

    @NonFinal
    @Value("${jwt.refreshable-duration}")
    int REFRESHABLE_DURATION;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserClient userClient;


    private String generateToken(UserClone userClone) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS384);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(userClone.getUsername())
                .issuer("engapp.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(VALIDATION_DURATION, ChronoUnit.SECONDS).toEpochMilli()
                ))
                .jwtID(UUID.randomUUID().toString())
                .claim("username", userClone.getUsername())
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot create token", e);
            throw new RuntimeException(e);
        }
    }

    public String authenticate(AuthenticationRequest authenticationRequest) {
        SecureUserRequest secureUserRequest = new SecureUserRequest(authenticationRequest.getUsername(), KeySecure.KEY_SECURE.getKey());
        UserClone userClone = this.userClient.getUserByUsername(secureUserRequest).getData();

        boolean authenticated = passwordEncoder.matches(authenticationRequest.getPassword(), userClone.getPassword());

        if (!authenticated) throw new ApplicationException(ErrorCode.UNAUTHENTICATED);

        return generateToken(userClone);
    }
}
