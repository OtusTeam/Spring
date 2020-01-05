package ru.otus.work.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    private Long id;
    private String name;
    private String description;
    private Author author;
    private Genre genre;
}
