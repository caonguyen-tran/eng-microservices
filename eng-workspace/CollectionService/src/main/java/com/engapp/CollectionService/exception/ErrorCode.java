package com.engapp.CollectionService.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorCode {
    RUNTIME_EXCEPTION(1007, "RUNTIME EXCEPTION!", HttpStatus.INTERNAL_SERVER_ERROR),
    VALUE_NOT_NULL(1008, "Null value !", HttpStatus.BAD_REQUEST),
    TRUST_FAIL(1001, "TRUST FAIL!", HttpStatus.UNAUTHORIZED),
    NOT_EXIST(1002, "Not Exist !", HttpStatus.NOT_FOUND),
    NOT_ACCEPTABLE(1003, "Not Acceptable !", HttpStatus.NOT_ACCEPTABLE),
    ;
    int code;

    String message;

    HttpStatus httpStatus;
}
