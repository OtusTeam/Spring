package ru.otus.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.model.Genre;
import ru.otus.spring.repository.GenreRepositoryJpa;

@Service
public class GenreService {
    private GenreRepositoryJpa genreRepositoryJpa;

    @Autowired
    public GenreService(GenreRepositoryJpa genreRepositoryJpa) {
        this.genreRepositoryJpa = genreRepositoryJpa;
    }

    public List<Genre> getAll() {
        return genreRepositoryJpa.findAll();
    }

    public Optional<Genre> findById(long id) {
        return genreRepositoryJpa.findById(id);
    }

    public void add(String name) {
        genreRepositoryJpa.save(new Genre(0L, name));
    }

    public void deleteById(long id) {
        genreRepositoryJpa.deleteById(id);
    }
}
