package ru.otus.spring.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.domain.Person;
import ru.otus.spring.repostory.PersonRepository;
import ru.otus.spring.rest.dto.PersonDto;
import ru.otus.spring.rest.exceptions.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PersonController {

    private final PersonRepository repository;

    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public List<PersonDto> getAllPersons() {
        return repository.findAll().stream()
                .map(PersonDto::toDto)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/persons", method = RequestMethod.GET, params = "name")
    public PersonDto getPersonByNameInRequest(@RequestParam("name") String name) {
        Person person = repository.findByName(name).stream().findFirst().orElseThrow(NotFoundException::new);
        return PersonDto.toDto(person);
    }

    @GetMapping("/persons/{id}")
    public PersonDto getPersonByIdInPath(@PathVariable("id") long id) {
        Person person = repository.findById(id).orElseThrow(NotFoundException::new);
        return PersonDto.toDto(person);
    }

    @PostMapping("/persons")
    public PersonDto createNewPerson(@RequestBody PersonDto dto) {
        Person person = PersonDto.toDomainObject(dto);
        Person savedPerson = repository.save(person);
        return PersonDto.toDto(savedPerson);
    }

    @PatchMapping("/persons/{id}/name")
    public PersonDto updateNameById(@PathVariable("id") long id, @RequestParam("name") String name) {
        Person person = repository.findById(id).orElseThrow(NotFoundException::new);
        person.setName(name);
        return PersonDto.toDto(repository.save(person));
    }

    @DeleteMapping("/persons/{id}")
    public void deleteById(@PathVariable("id") long id) {
        repository.deleteById(id);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFound(NotFoundException ex) {
        return ResponseEntity.badRequest().body("Таких тут нет!");
    }
}
