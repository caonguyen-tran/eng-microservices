package com.engapp.SecurityService.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorCode {
    PASSWORD_NOT_NULL(2005, "PASSWORD IS NULL!", HttpStatus.BAD_REQUEST),
    RUNTIME_EXCEPTION(2006, "RUNTIME EXCEPTION!", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHENTICATED(2007, "UNAUTHENTICATED!", HttpStatus.BAD_REQUEST),
    JWT_PARSER_FAIL(2008, "JWT PARSER_FAIL!", HttpStatus.BAD_REQUEST),
    ;

    @Value("${spring.application.name}")
    String APPLICATION_NAME;

    int code;

    String message;

    String applicationName;

    HttpStatus httpStatus;

    ErrorCode(int code, String message, HttpStatus httpStatus){
        this.code = code;
        this.applicationName = APPLICATION_NAME;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
