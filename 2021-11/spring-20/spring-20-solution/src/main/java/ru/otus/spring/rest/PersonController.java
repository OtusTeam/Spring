package ru.otus.spring.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Person;
import ru.otus.spring.repository.PersonRepository;
import ru.otus.spring.rest.dto.PersonConverter;
import ru.otus.spring.rest.dto.PersonDto;

import static org.springframework.http.ResponseEntity.notFound;

@RestController
public class PersonController {

    private final PersonRepository repository;
    private final PersonConverter converter;

    public PersonController(PersonRepository repository, PersonConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @GetMapping("/person")
    public Flux<PersonDto> all() {
        return repository.findAll()
                .map(converter::convertToDto)
                // --0-0-0---X------
                // --0-0-0---|------
                .onErrorResume(Exception.class, (e) -> Flux.empty());
    }

    @GetMapping("/person/{id}")
    public Mono<ResponseEntity<PersonDto>> byId(
            @PathVariable("id") String id) {
        // ---p|---
        // ---D|---
        // ---R|---

        // ----|---
        // ????????
        // ----R|---
        return repository.findById(id)
                .map(converter::convertToDto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(notFound().build());
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
    public Mono<Person> save(@RequestBody Person dto) {
        return repository.save(dto);
    }
}
