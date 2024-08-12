package com.engapp.SecurityService.exception;

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
    PASSWORD_NOT_NULL(2005, "PASSWORD IS NULL!", HttpStatus.BAD_REQUEST),
    RUNTIME_EXCEPTION(2006, "RUNTIME EXCEPTION!", HttpStatus.INTERNAL_SERVER_ERROR),
    ;
    int code;

    String message;

    HttpStatus httpStatus;
}
