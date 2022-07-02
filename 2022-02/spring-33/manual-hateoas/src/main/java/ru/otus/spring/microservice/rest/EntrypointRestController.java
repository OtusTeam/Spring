package ru.otus.spring.microservice.rest;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class EntrypointRestController {
    @GetMapping("api/entrypoint")
    public ResponseEntity<?> getApiEntrypoint() {
        var link = linkTo(methodOn(PersonsRestController.class).findAllPersons()).withRel("all");
        return new ResponseEntity<>(link, HttpStatus.OK);
    }
}
