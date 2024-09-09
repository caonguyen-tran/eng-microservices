package com.engapp.AdminService.exception;

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
    USER_NOT_FOUND(1005, "USER NOT FOUND!", HttpStatus.NOT_FOUND),
    USERNAME_NOT_NULL(1005, "USERNAME IS NULL!", HttpStatus.BAD_REQUEST),
    PASSWORD_NOT_NULL(1005, "PASSWORD IS NULL!", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1006, "Username must be at least 6 characters!", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1006, "Password must be at least 6 characters!", HttpStatus.BAD_REQUEST),
    ROLE_NOT_EXISTS(1303, "Role not exists", HttpStatus.BAD_REQUEST),
    PERMISSION_NOT_EXISTS(1304, "Permission not exists", HttpStatus.BAD_REQUEST),
    ROLE_EXISTS(1403, "Role already existed!", HttpStatus.BAD_REQUEST),
    PERMISSION_EXISTS(1403, "Permission already existed!", HttpStatus.BAD_REQUEST),
    USER_EXISTS(1009, "User already existed!", HttpStatus.BAD_REQUEST),
    DUPLICATED_PASSWORD(1802, "Your old password is duplicated with the new password!", HttpStatus.BAD_REQUEST),
    WRONG_PASSWORD(1501, "Password is wrong!", HttpStatus.BAD_REQUEST),
    WRONG_VALUE(1502, "Value is wrong!", HttpStatus.BAD_REQUEST),
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
