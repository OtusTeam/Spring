package com.example.homework11_webflux.exception;

public class WordNotFoundException extends Exception{
    public WordNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
