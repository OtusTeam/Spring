package ru.otus.spring.dto;

import lombok.Data;
import ru.otus.spring.models.Author;
import ru.otus.spring.models.Book;
import ru.otus.spring.models.Comment;
import ru.otus.spring.models.Genre;

import java.util.List;

@Data
public class BookDto {

    private long id;
    private String name;
    private List<String> comments;
    private List<String> authors;
    private List<String> genres;

    public BookDto(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.comments = book.getComments().stream().map(Comment::getText).toList();
        this.authors = book.getAuthors().stream().map(Author::getName).toList();
        this.genres = book.getGenres().stream().map(Genre::getName).toList();
    }
}
