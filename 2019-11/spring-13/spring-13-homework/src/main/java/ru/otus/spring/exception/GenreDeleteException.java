package ru.otus.spring.exception;

public class GenreDeleteException extends RuntimeException {
    public GenreDeleteException(String message) {
        super(message);
    }
}
