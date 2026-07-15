package ru.otus.spring.controller;

public class NotFoundException extends RuntimeException{

    NotFoundException() {
        super("Person not found");
    }
}
