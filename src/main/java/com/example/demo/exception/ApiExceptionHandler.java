/*
package com.example.demo.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.ZoneId;
import java.time.ZonedDateTime;

//TODO А этот Handler будет использоваться всеми контроллерами? Если да, то нам так не нужно. Нужно только для Dog
// ExceptionHandler методы в контроллере
@ControllerAdvice
public class ApiExceptionHandler {

    //TODO Это что?
    private HttpStatus badRequest = HttpStatus.BAD_REQUEST;

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e){
        ApiException apiException = new ApiException(e.getMessage(), badRequest, ZonedDateTime.now(ZoneId.of("UTC+7")));
        return new ResponseEntity<>(apiException, badRequest);
    }

    //TODO В каком случае бросается это исключение?
    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(){
        ApiException apiException = new ApiException("handleMethodArgumentTypeMismatchException: Failed to convert value to required type.", badRequest, ZonedDateTime.now(ZoneId.of("UTC+7")));
        return new ResponseEntity<>(apiException, badRequest);
    }

    //TODO В каком случае бросается это исключение?
    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolationException(){
        ApiException apiException = new ApiException("handleConstraintViolationException: not valid arguments.", badRequest, ZonedDateTime.now(ZoneId.of("UTC+7")));
        return new ResponseEntity<>(apiException, badRequest);
    }

    //TODO А что, если будет исключение, какого-то другого типа?
}
*/
