package ru.otus.spring.rest.dto;

import org.springframework.stereotype.Component;
import ru.otus.spring.domain.Person;

@Component
public class PersonConverter {

    public PersonDto convertToDto(Person domain) {
        return new PersonDto(
                domain.getId(),
                domain.getLastName(),
                domain.getAge()
        );
    }
 }
