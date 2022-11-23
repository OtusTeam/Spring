package ru.otus.spring.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonDto {

    private final String id;

    @JsonProperty("name")
    private final String name;

    private final int age;

    public PersonDto(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
