package ru.otus.spring.dto;

import lombok.Data;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Genre;

import java.util.List;

@Data
public class BookDto {

    private long id;
    private String name;
    private List<Author> authorList;
    private List<Genre> genreList;
}
