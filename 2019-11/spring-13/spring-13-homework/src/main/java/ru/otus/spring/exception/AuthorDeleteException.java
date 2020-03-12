package ru.otus.spring.exception;

public class AuthorDeleteException extends RuntimeException {
    public AuthorDeleteException(String message) {
        super(message);
    }
}
