package ru.otus.spring.domain;


import java.util.List;

public record PersonDto(Long id, String name, Integer age, List<String> notes) {

}
