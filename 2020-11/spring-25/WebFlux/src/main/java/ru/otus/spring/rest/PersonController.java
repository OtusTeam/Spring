package ru.otus.spring.rest;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.data.Person;
import ru.otus.spring.data.PersonRepository;

@RestController
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/person")
    public Flux<Person> getAll() {
        return personRepository.findAll();
    }

    @GetMapping("/person/find")
    public Mono<Person> find(@RequestParam("name") String name) {
        return personRepository.findByName(name)
                .cache();
    }


    @PostMapping("/person")
    public Mono<Person> savePerson(@RequestBody Person person) {
        return personRepository.save(person);
    }
}
