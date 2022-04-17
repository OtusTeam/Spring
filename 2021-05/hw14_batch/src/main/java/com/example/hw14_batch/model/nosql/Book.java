package com.example.hw14_batch.model.nosql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "books")
public class Book {
    @Id
    private String id;

    @Field(name = "title")
    private String title;

    private Author author;

    private Genre genre;

    private List<Comment> comments;

    public Book(String title, Author author, Genre genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public Book(String title, Author author, Genre genre, List<Comment> comments) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.comments = comments;

    }

}
