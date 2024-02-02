package ru.otus.spring.rest;

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

}
