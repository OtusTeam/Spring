package ru.otus.spring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.domain.Person;
import ru.otus.spring.repostory.PersonRepository;
import ru.otus.spring.rest.dto.PersonDto;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PersonController {

    private final PersonRepository repository;

    @Autowired
    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/persons")
    public List<PersonDto> getAllPersons() {
        return repository.findAll().stream().map(PersonDto::toDto)
                .collect(Collectors.toList());
    }
}
