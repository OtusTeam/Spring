package ru.otus.spring.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import ru.otus.spring.domain.Person;

@Data
@AllArgsConstructor
public class PersonDto {

    private long id;
    @Length(min = 3)
    private String name;

    public static PersonDto toDto(Person person) {
        return new PersonDto(person.getId(), person.getName());
    }

    public Person toDomainObject() {
        return new Person(id, name);
    }
}
