package com.engapp.SecurityService.service;

import com.engapp.SecurityService.constant.KeySecure;
import com.engapp.SecurityService.dto.clone.RoleClone;
import com.engapp.SecurityService.dto.clone.UserClone;
import com.engapp.SecurityService.dto.reponse.IntrospectResponse;
import com.engapp.SecurityService.dto.request.AuthenticationRequest;
import com.engapp.SecurityService.dto.request.IntrospectRequest;
import com.engapp.SecurityService.dto.request.SecureUserRequest;
import com.engapp.SecurityService.exception.ApplicationException;
import com.engapp.SecurityService.exception.ErrorCode;
import com.engapp.SecurityService.feign.UserClient;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Set;
import java.util.StringJoiner;
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
                .claim("id", userClone.getId())
                .issuer("engapp.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(VALIDATION_DURATION, ChronoUnit.MINUTES).toEpochMilli()
                ))
                .jwtID(UUID.randomUUID().toString())
                .claim("roles", buildRoles(userClone.getRoles()))
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

    public IntrospectResponse introspect(IntrospectRequest introspectRequest) {
        boolean isValid = true;
        String token = authorizationHeaderHandler(introspectRequest.getToken());

        try {
            verifyToken(token);
        } catch (ApplicationException | JOSEException | ParseException e) {
            isValid = false;
        }

        return new IntrospectResponse(token, isValid);
    }

    private void verifyToken(String token) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        boolean verified = signedJWT.verify(verifier);

        if (!(verified && expiryTime.after(new Date()))) throw new ApplicationException(ErrorCode.UNAUTHENTICATED);
    }

    public String buildRoles(Set<RoleClone> roles) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        roles.forEach(roleClone -> {
            stringJoiner.add(roleClone.getName());
        });
        return stringJoiner.toString();
    }

    public String authorizationHeaderHandler(String bearerToken){
        if(bearerToken == null || bearerToken.isEmpty()) {
            return null;
        }
        return bearerToken.replace("Bearer ", "");
    }
}
