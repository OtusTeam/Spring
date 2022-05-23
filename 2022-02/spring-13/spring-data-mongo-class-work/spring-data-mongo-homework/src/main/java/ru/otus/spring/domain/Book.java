package ru.otus.spring.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@Document(collection = "books")
public class Book implements Serializable {

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
