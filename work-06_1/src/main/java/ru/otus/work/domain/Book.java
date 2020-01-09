package ru.otus.work.domain;

import lombok.*;

@Getter
@Setter
@Builder
public class Book {
    private Long id;
    private String name;
    private String description;
    private Author author;
    private Genre genre;
}
