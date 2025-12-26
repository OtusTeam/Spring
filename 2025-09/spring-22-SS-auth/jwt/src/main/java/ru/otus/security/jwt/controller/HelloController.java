package ru.otus.security.jwt.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello( Authentication authentication ) {
        return "Hello, " + authentication.getName() + "!";
    }
}
