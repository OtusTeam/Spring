package ru.otus.spring.rest;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Person;
import ru.otus.spring.repository.PersonRepository;

@RestController
public class PersonController {

    private PersonRepository repository;

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

    @GetMapping("/person/byname")
    public Flux<Person> byName(@RequestParam("name") String lastName) {
        return repository.findAllByLastName(lastName);
    }

    @GetMapping("/person/byage")
    public Flux<Person> byAge(@RequestParam int age) {
        return repository.findAllByAge(age);
    }

    @PostMapping("/person")
    public Mono<Person> save(@RequestBody Mono<Person> dto) {
        return repository.save(dto);
    }
}
