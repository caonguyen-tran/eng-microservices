package com.engapp.ApiGateway.configuration;

import com.engapp.ApiGateway.dto.response.ApiStructResponse;
import com.engapp.ApiGateway.service.SecurityClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Component
@Slf4j
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AuthenticationConfiguration implements GlobalFilter {
    @Autowired
    private SecurityClientService securityClientService;
    @Autowired
    private ObjectMapper objectMapper;

    //PUBLIC ENDPOINTS which do not need authorization token (Bearer token)
    //allow accessible public endpoints of other services when passed api gateway

    @NonFinal
    private final static String[] PUBLIC_ENDPOINTS = {
            "/security-service/auth/token",
            "/user-service/user/register-user"
    };

    @NonFinal
    @Value("${app.api-prefix}")
    private String apiPrefix;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest serverHttpRequest = exchange.getRequest();

        if(isPublicEndpoint(serverHttpRequest)) {
            return chain.filter(exchange);
        }

        String token = getAuthorizationToken(serverHttpRequest);
        if(token == null) {
            return unauthenticated(exchange.getResponse());
        }

        return securityClientService.introspect(token).flatMap(introspectResponse -> {
            if(introspectResponse.getData().isValid()){
                return chain.filter(exchange);
            }
            return unauthenticated(exchange.getResponse());
        }).onErrorResume(throwable -> unauthenticated(exchange.getResponse()));
    }

    public String getAuthorizationToken(ServerHttpRequest serverHttpRequest) {
        String authorizationHeader = serverHttpRequest.getHeaders().getFirst("Authorization");
        log.info(authorizationHeader);
        if(authorizationHeader == null || authorizationHeader.isEmpty()) {
            return null;
        }
        return authorizationHeader.substring(7);
    }

    Mono<Void> unauthenticated(ServerHttpResponse response){
        ApiStructResponse<?> apiResponse = ApiStructResponse.builder()
                .code(2713)
                .message("UNAUTHENTICATED FROM API-GATEWAY!")
                .build();

        String body = null;
        try {
            body = objectMapper.writeValueAsString(apiResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        return response.writeWith(Mono.just(response.bufferFactory().wrap(body.getBytes())));
    }

    public boolean isPublicEndpoint(ServerHttpRequest serverHttpRequest) {
        String urlRequest = serverHttpRequest.getURI().getPath();
        return Arrays.stream(PUBLIC_ENDPOINTS).anyMatch(endpoint -> urlRequest.matches(apiPrefix + endpoint));
    }
}
