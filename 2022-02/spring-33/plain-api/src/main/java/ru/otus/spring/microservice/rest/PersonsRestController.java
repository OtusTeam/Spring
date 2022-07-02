package ru.otus.spring.microservice.rest;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.microservice.domain.Person;
import ru.otus.spring.microservice.repostory.PersonRepository;

import java.util.List;

@RestController
public class PersonsRestController {

    private final PersonRepository repository;

    public PersonsRestController(PersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("api/persons")
    public ResponseEntity<?> findAllPersons() {
        List<Person> persons = repository.findAll();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @GetMapping("api/persons/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") int id) {
        Person person = repository.findById(id).orElseThrow();
        return new ResponseEntity<>(person, HttpStatus.OK);
    }
}
