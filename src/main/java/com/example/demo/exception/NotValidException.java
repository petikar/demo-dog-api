package com.example.demo.exception;

public class NotValidException extends RuntimeException{
    public NotValidException(String s) {
        super(s);
    }
}
