//package com.engapp.ApiGateway.repository.httpClient;
//
//
//import com.engapp.ApiGateway.dto.request.IntrospectRequest;
//import com.engapp.ApiGateway.dto.response.ApiStructResponse;
//import com.engapp.ApiGateway.dto.response.IntrospectResponse;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.service.annotation.PostExchange;
//import reactor.core.publisher.Mono;
//
//@FeignClient(name="security-service", url = "http://localhost:8081/security-service")
//public interface SecurityClient {
//    @PostMapping(value = "/auth/introspect", produces = MediaType.APPLICATION_JSON_VALUE)
//    Mono<ApiStructResponse<IntrospectResponse>> introspect(@RequestBody IntrospectRequest introspectRequest);
//}
