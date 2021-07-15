package com.example.demo.exception;

public class ApiRequestException extends RuntimeException{

    public ApiRequestException(String s) {
        super(s);
    }
}
