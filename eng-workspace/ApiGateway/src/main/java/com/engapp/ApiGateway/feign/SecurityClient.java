package com.engapp.ApiGateway.feign;

import com.engapp.ApiGateway.dto.response.ApiStructResponse;
import com.engapp.ApiGateway.dto.response.IntrospectResponse;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

public interface SecurityClient {
    @PostExchange(url = "/auth/introspect")
    Mono<ApiStructResponse<IntrospectResponse>> introspect(@RequestHeader(value="Authorization") String token);
}
