package ru.otus.spring.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@EqualsAndHashCode(of = "id")
@Document(collection = "books")
public class Book {

    @Id
    private String id;
    private String name;
    private List<Comment> comments;
    private List<Author> authors;
    private List<Genre> genres;

    public Book(String name, List<Comment> comments, List<Author> authors, List<Genre> genres) {
        this.name = name;
        this.comments = comments;
        this.authors = authors;
        this.genres = genres;
    }
}
