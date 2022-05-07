package ru.otus.spring.dto;

import ru.otus.spring.domain.Person;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PersonDto {

    private int id;

    @NotBlank(message = "{name-field-should-not-be-blank}")
    @Size(min = 2, max = 10, message = "{name-field-should-has-expected-size}")
    private String name;

    public PersonDto() {
    }

    public PersonDto(String name) {
        this.name = name;
    }

    public PersonDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person toDomainObject(){
        return new Person(id, name);
    }

    public static PersonDto fromDomainObject(Person person) {
        return new PersonDto(person.getId(), person.getName());
    }
}
