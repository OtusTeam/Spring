package ru.otus.spring.service;

import ru.otus.spring.domain.Author;
import ru.otus.spring.rest.dto.resposne.AuthorBookResponseDto;

import java.util.List;

public interface AuthorService {

    Author saveIfNotExists(Author author);

    List<AuthorBookResponseDto> getAllAuthorsByBookId(long id);

}
