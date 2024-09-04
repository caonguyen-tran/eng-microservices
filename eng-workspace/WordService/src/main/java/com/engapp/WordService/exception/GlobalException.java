package com.engapp.WordService.exception;

import com.engapp.WordService.dto.response.ApiStructResponse;
import com.mongodb.MongoWriteException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalException {

    @ExceptionHandler(value=Exception.class)
    public ResponseEntity<ApiStructResponse<String>> handlerException(Exception exception){
        log.info(exception.getMessage());
        ApiStructResponse<String> response = new ApiStructResponse<>();

        response.setCode(ErrorCode.RUNTIME_EXCEPTION.getCode());
        response.setMessage("Runtime Exception");
        response.setData(exception.getMessage());

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(value = ApplicationException.class)
    public ResponseEntity<ApiStructResponse<String>> handlerApplicationException(ApplicationException applicationException){
        ErrorCode errorCode = applicationException.getErrorCode();
        ApiStructResponse<String> response = new ApiStructResponse<>();
        response.setCode(errorCode.getCode());
        response.setMessage(errorCode.getMessage());
        response.setData(errorCode.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiStructResponse<String>> handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        String keyEnum = exception.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.valueOf(keyEnum);

        ApiStructResponse<String> response = new ApiStructResponse<>();

        response.setCode(errorCode.getCode());
        response.setMessage("Method Argument Not Valid Exception");
        response.setData(errorCode.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(value= MongoWriteException.class)
    public ResponseEntity<ApiStructResponse<String>> handlerDuplicateKeyException(MongoWriteException exception){
        ApiStructResponse<String> response = new ApiStructResponse<>();
        response.setCode(exception.getCode());
        response.setMessage("Duplicate value!");
        response.setData(exception.getMessage());
        return ResponseEntity.badRequest().body(response);
    }
}
