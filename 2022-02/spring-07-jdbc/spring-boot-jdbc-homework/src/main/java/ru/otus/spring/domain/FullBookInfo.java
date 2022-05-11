package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FullBookInfo {

    private long id;
    private String name;
    private List<Author> authorList;
    private List<Genre> genreList;
}
