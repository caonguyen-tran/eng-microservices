package com.engapp.WordService.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorCode implements Serializable {
    RUNTIME_EXCEPTION(1007, "RUNTIME EXCEPTION!", HttpStatus.INTERNAL_SERVER_ERROR),
    VALUE_NOT_NULL(1008, "Null value !", HttpStatus.BAD_REQUEST),
    TRUST_FAIL(1001, "TRUST FAIL!", HttpStatus.UNAUTHORIZED),
    NOT_EXIST(1002, "Not Exist !", HttpStatus.NOT_FOUND),
    NOT_ACCEPTABLE(1003, "Not Acceptable !", HttpStatus.NOT_ACCEPTABLE),
    ALREADY_EXIST(1004, "Already Exist !", HttpStatus.CONFLICT),
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
