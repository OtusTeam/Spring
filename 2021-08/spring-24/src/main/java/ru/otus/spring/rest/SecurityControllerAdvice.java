package ru.otus.spring.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@ControllerAdvice
public class SecurityControllerAdvice {
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> accessError(){
        return ResponseEntity.of( Optional.of( "Неудачник" ));
    }
}
