package ru.otus.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.models.Genre;
import ru.otus.spring.repositories.GenreRepository;

@Service
public class GenreService {
    private GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

    public Optional<Genre> findById(long id) {
        return genreRepository.findById(id);
    }

    public void add(String name) {
        genreRepository.save(new Genre(0L, name));
    }

    public void deleteById(long id) {
        genreRepository.deleteById(id);
    }
}
