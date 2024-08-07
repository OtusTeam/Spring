package ru.otus.spring.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.repostory.PersonRepository;
import ru.otus.spring.rest.dto.PersonDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class PersonController {

    private final PersonRepository repository;

    @GetMapping("/api/persons")
    public List<PersonDto> getAllPersons() {
        return repository.findAll().stream().map(PersonDto::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/api/persons")
    public PersonDto addPerson(@Valid @RequestBody PersonDto personDto) {
        var savedPerson = repository.save(personDto.toDomainObject());
        return PersonDto.toDto(savedPerson);
    }
}
