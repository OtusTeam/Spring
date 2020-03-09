package ru.otus.work.model.jdbc;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Book {
    private Long id;
    private String name;
    private String description;
    private Author author;
    private Genre genre;
}
