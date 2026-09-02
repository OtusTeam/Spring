package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.otus.spring.rest.dto.request.CreateFullBookInfoRequestDto;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    private String id;
    private String name;
    private List<Comment> comments = new ArrayList<>();
    private List<Author> authors = new ArrayList<>();
    private List<Genre> genres = new ArrayList<>();

    public Book(String name, List<Comment> comments, List<Author> authors, List<Genre> genres) {
        this.name = name;
        this.comments = comments;
        this.authors = authors;
        this.genres = genres;
    }

    public Book(CreateFullBookInfoRequestDto dto) {
        this.name = dto.getName();
        this.comments = List.of(new Comment(dto.getCommentText()));
        this.authors = List.of(new Author(dto.getAuthorName()));
        this.genres = List.of(new Genre(dto.getGenreName()));
    }
}
