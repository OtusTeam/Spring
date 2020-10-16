package ru.otus.spring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.domain.Person;
import ru.otus.spring.repostory.PersonRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PersonController {

    private final PersonRepository repository;

    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(
            value = "/person",
            method = RequestMethod.GET
    )
    public List<PersonDto> get() {
        return repository.findAll().stream()
                .map(PersonDto::toDto)
                .collect(Collectors.toList());
    }

    @RequestMapping(
            value = "/person/{id}",
            method = RequestMethod.GET
    )
    public PersonDto get(
            @PathVariable("id") int id
    ) {
        Person person = repository.findById(id).orElseThrow(NotFoundException::new);
        return PersonDto.toDto(person);
    }

    @RequestMapping(
            value = "/person",
            method = RequestMethod.POST
    )
    public @ResponseBody PersonDto create(@RequestBody PersonDto dto) {
        Person account = PersonDto.toDomainObject(dto);
        Person accountWithId = repository.save(account);
        return PersonDto.toDto(accountWithId);
    }

    @DeleteMapping("/person/{id}")
    public void delete(@PathVariable("id") int id) {
        repository.deleteById(id);
    }

    @PutMapping("/person/{id}/holder")
    public void changeName(
            @PathVariable("id") int id,
            @RequestParam("name") String name
    ) {
        Person person = repository.findById(id).orElseThrow(NotFoundException::new);
        person.setName(name);
        repository.save(person);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFound(NotFoundException ex) {
        return ResponseEntity.badRequest().body("Not found");
    }
}
