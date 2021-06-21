package ru.otus.spring.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Person {
    private final long id;
    private final String name;
}
