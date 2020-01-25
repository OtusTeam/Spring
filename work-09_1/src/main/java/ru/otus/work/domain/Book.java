package ru.otus.work.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Document(collection = "books")
public class Book {
    @Id
    private String id;

    private String name;

    private String description;

    @DBRef
    private List<Author> authors;

    @DBRef
    private Genre genre;

    public Book() {
    }
}
