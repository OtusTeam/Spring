package ru.otus.spring.rest;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Person;
import ru.otus.spring.repository.PersonRepository;

@RestController
public class PersonController {

    private final PersonRepository repository;

    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/person")
    public Flux<Person> all() {
        return repository.findAll();
    }

    @GetMapping("/person/{id}")
    public Mono<Person> byId(@PathVariable("id") String id) {
        return repository.findById(id);
    }

    @PostMapping("/person")
    public Mono<Person> save(@RequestBody Mono<Person> dto) {
        return repository.save(dto);
    }

    @GetMapping("/person/find")
    public Flux<Person> byName(@RequestParam("name") String name) {
        return repository.findAllByLastName(name);
    }
}
