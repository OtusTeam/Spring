package ru.otus.spring.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.otus.spring.rest.exceptions.NotFoundException;

@RequiredArgsConstructor
@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String ERROR_STRING = "Тут пёрсонов нет(";

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handeNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(404).body(ERROR_STRING);
    }

}
