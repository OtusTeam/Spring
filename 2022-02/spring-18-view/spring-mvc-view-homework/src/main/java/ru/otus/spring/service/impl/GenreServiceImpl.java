package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repositories.GenreRepository;
import ru.otus.spring.service.GenreService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository repository;

    @Override
    @Transactional
    public Genre saveIfNotExists(Genre genre) {
        return repository.findByName(genre.getName()).orElseGet(() -> repository.save(new Genre(genre.getName())));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> getAllGenresByBookId(long id) {
        return repository.getAllGenresByBookId(id);
    }
}
