package ru.otus.spring.service;

import java.util.List;

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

    public List<Genre> findByName(String name) {
        return genreRepository.findByName(name);
    }

    public void add(String name) {
        genreRepository.save(new Genre(name));
    }

    public void deleteGenresByName(String name) { genreRepository.deleteGenresByName(name);}
}
