package ru.otus.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.spring.domain.Person;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class PersonDto {

    private long id;

    @NotBlank(message = "{name-field-should-not-be-blank}")
    @Size(min = 2, max = 10, message = "{name-field-should-has-expected-size}")
    private String name;

    public Person toDomainObject(){
        return new Person(id, name);
    }

    public static PersonDto fromDomainObject(Person person) {
        return new PersonDto(person.getId(), person.getName());
    }
}
