package com.engapp.UserService.exception;

import com.engapp.UserService.dto.response.ApiStructResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalException {

    @ExceptionHandler(value=Exception.class)
    public ResponseEntity<ApiStructResponse<String>> handlerException(Exception exception){
        ApiStructResponse response = new ApiStructResponse();

        response.setCode(500);
        response.setMessage("INTERNAL EXCEPTION");
        response.setData(exception.getMessage());
        return ResponseEntity.badRequest().body(response);
    }
}
