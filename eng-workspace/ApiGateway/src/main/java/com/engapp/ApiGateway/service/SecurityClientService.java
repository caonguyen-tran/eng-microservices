package com.engapp.ApiGateway.service;

import com.engapp.ApiGateway.dto.response.ApiStructResponse;
import com.engapp.ApiGateway.dto.response.IntrospectResponse;
import com.engapp.ApiGateway.feign.SecurityClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SecurityClientService {
    @Autowired
    private SecurityClient securityClient;

    public Mono<ApiStructResponse<IntrospectResponse>> introspect(String token){
        return securityClient.introspect(token);
    }
}
