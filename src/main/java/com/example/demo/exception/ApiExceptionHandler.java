package com.example.demo.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    HttpStatus badRequest = HttpStatus.BAD_REQUEST;

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e){
        ApiException apiException = new ApiException(e.getMessage(), badRequest, ZonedDateTime.now(ZoneId.of("UTC+7")));
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(){
        ApiException apiException = new ApiException("handleMethodArgumentTypeMismatchException: Failed to convert value to required type.", badRequest, ZonedDateTime.now(ZoneId.of("UTC+7")));
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolationException(){
        ApiException apiException = new ApiException("handleConstraintViolationException: not valid arguments.", badRequest, ZonedDateTime.now(ZoneId.of("UTC+7")));
        return new ResponseEntity<>(apiException, badRequest);
    }
}
