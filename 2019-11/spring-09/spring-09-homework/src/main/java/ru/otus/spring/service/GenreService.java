package ru.otus.spring.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<String> getAll() {
        return genreRepository.findAll().stream()
                .map(g -> String.format("%s - %s", g.getId(), g.getName()))
                .collect(Collectors.toList());
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
