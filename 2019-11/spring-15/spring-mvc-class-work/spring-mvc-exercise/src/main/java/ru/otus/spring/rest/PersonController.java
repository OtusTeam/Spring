package ru.otus.spring.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.domain.Person;
import ru.otus.spring.repostory.PersonRepository;

@RestController
public class PersonController {

    @Autowired
    private final PersonRepository repository;

    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/person")
    public List<PersonDto> get() {
        return repository.findAll().stream()
                .map(PersonDto::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/person/{id}")
    public PersonDto getById(
            @PathVariable("id") int id
    ) {
        Person person = repository.findById(id).orElseThrow(NotFoundException::new);
        return PersonDto.toDto(person);
    }

    @PostMapping("/person")
    public @ResponseBody PersonDto create(@RequestBody PersonDto dto) {
        Person person = PersonDto.toDomainObject(dto);
        Person personWithId = repository.save(person);
        return PersonDto.toDto(personWithId);
    }
}
