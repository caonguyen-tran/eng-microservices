package com.engapp.UserService.exception;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorCode {
    USER_NOT_FOUND(2005, "USER NOT FOUND!", HttpStatus.NOT_FOUND),
    USERNAME_NOT_NULL(2005, "USERNAME IS NULL!", HttpStatus.BAD_REQUEST),
    PASSWORD_NOT_NULL(2005, "PASSWORD IS NULL!", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(2006, "Username must be at least 6 characters!", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(2006, "Password must be at least 6 characters!", HttpStatus.BAD_REQUEST),
    RUNTIME_EXCEPTION(3007, "RUNTIME EXCEPTION!", HttpStatus.INTERNAL_SERVER_ERROR),
    ROLE_NOT_EXISTS(3303, "Role not exists", HttpStatus.BAD_REQUEST),
    PERMISSION_NOT_EXISTS(3304, "Permission not exists", HttpStatus.BAD_REQUEST),
    ROLE_EXISTS(3403, "Role already existed!", HttpStatus.BAD_REQUEST),
    PERMISSION_EXISTS(3403, "Permission already existed!", HttpStatus.BAD_REQUEST),
    VALUE_NOT_NULL(2008, "Null value !", HttpStatus.BAD_REQUEST),
    USER_EXISTS(2009, "User already existed!", HttpStatus.BAD_REQUEST),
    ;
    int code;

    String message;

    HttpStatus httpStatus;
}
