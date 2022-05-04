package ru.otus.spring.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class BookGenreLink {

    private final long id;
    private final long bookId;
    private final long genreId;
}
