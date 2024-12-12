package ru.otus.spring.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Notes;
import ru.otus.spring.domain.Person;
import ru.otus.spring.domain.PersonDto;
import ru.otus.spring.repository.NotesRepository;
import ru.otus.spring.repository.PersonRepository;
import ru.otus.spring.repository.PersonRepositoryCustom;

import java.util.List;

@RestController
public class PersonController {

    private final PersonRepository personRepository;
    private final NotesRepository notesRepository;

    private final PersonRepositoryCustom personRepositoryCustom;

    public PersonController(PersonRepository personRepository, NotesRepository notesRepository, PersonRepositoryCustom personRepositoryCustom) {
        this.personRepository = personRepository;
        this.notesRepository = notesRepository;
        this.personRepositoryCustom = personRepositoryCustom;
    }

    @GetMapping("/person")
    public Flux<PersonDto> all() {
        return personRepositoryCustom.findAll();
    }

    @GetMapping("/person/{id}")
    public Mono<ResponseEntity<PersonDto>> byId(@PathVariable("id") Long id) {
        return personRepository.findById(id)
                .flatMap(person -> notesRepository.findByPersonId(person.getId()).map(Notes::getNoteText).collectList()
                        .map(notes -> toDto(person, notes)))
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.fromCallable(() -> ResponseEntity.notFound().build()));
    }

    @PostMapping("/person")
    public Mono<Person> save(@RequestBody Mono<Person> dto) {
        return personRepository.save(dto);
    }

    @GetMapping("/person/find")
    public Flux<Person> byName(@RequestParam("name") String name) {
        return personRepository.findAllByLastName(name);
    }

    private PersonDto toDto(Person person, List<String> notes) {
        return new PersonDto(String.valueOf(person.getId()), person.getLastName(), person.getAge(), notes);
    }
}
