package ru.otus.spring.service;

import ru.otus.spring.domain.Genre;
import ru.otus.spring.rest.dto.resposne.GenreBookResponseDto;

import java.util.List;

public interface GenreService {

    Genre saveIfNotExists(Genre genre);

    List<GenreBookResponseDto> getAllGenresByBookId(long id);
}
