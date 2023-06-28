package ru.otus.spring.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Person;
import ru.otus.spring.domain.PersonDto;
import ru.otus.spring.repository.PersonRepository;

@RestController
public class PersonController {

    private final PersonRepository repository;

    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/person")
    public Flux<PersonDto> all() {
        return repository.findAll()
                .map(this::toDto);
    }

    @GetMapping("/person/{id}")
    public Mono<ResponseEntity<PersonDto>> byId(@PathVariable("id") Long id) {
        return repository.findById(id)
                .map(this::toDto)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.fromCallable(() -> ResponseEntity.notFound().build()));
    }

    @PostMapping("/person")
    public Mono<Person> save(@RequestBody Mono<Person> dto) {
        return repository.save(dto);
    }

    @GetMapping("/person/find")
    public Flux<Person> byName(@RequestParam("name") String name) {
        return repository.findAllByLastName(name);
    }

    private PersonDto toDto(Person person) {
        return new PersonDto(String.valueOf(person.getId()), person.getLastName(), person.getAge());
    }
}
