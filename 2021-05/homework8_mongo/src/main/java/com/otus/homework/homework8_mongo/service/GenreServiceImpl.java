package com.otus.homework.homework8_mongo.service;

import com.otus.homework.homework8_mongo.domain.Genre;
import com.otus.homework.homework8_mongo.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class GenreServiceImpl implements GenreService{
    final private GenreRepository repository;

    public GenreServiceImpl(@Autowired GenreRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Genre> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Genre> getById(String genreId) {
        return repository.findById(genreId);
    }

    @Override
    public Optional<Genre> getByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Genre save(Genre genre) {
        return repository.save(genre);
    }

    @Override
    public void deleteById(String genreId) {
        repository.deleteById(genreId);
    }
}
