package ru.otus.spring.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.rest.dto.PersonDto;
import ru.otus.spring.rest.exceptions.NotFoundException;
import ru.otus.spring.service.PersonService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class PersonController {

    private final PersonService personService;

    @GetMapping("/api/persons")
    public List<PersonDto> getAllPersons() {
        List<PersonDto> persons = personService.findAll().stream().map(PersonDto::toDto)
                .collect(Collectors.toList());
        if (persons.isEmpty()) {
            throw new NotFoundException("Persons not found!");
        }
        return persons;
    }

    @PostMapping("/api/persons")
    public ResponseEntity<PersonDto> addPerson(@Valid @RequestBody PersonDto personDto) {
        var savedPerson = personService.save(personDto.toDomainObject());
        return ResponseEntity.ok(PersonDto.toDto(savedPerson));
    }
}
