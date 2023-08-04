package ru.otus.spring.rest;

import ru.otus.spring.repostory.PersonRepository;


public class PersonController {

    private final PersonRepository repository;

    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }
}
