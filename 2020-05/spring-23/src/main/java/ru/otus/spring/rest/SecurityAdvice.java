package ru.otus.spring.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@ControllerAdvice
public class SecurityAdvice {
    private final Logger logger = LoggerFactory.getLogger( SecurityAdvice.class );

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAnyError( Exception e ) {
        logger.error( "some throuble", e );
        return ResponseEntity.of( Optional.of( "Неудачник" ) );
    }
}
