package com.example.homework7_datajpa.service;

import com.example.homework7_datajpa.model.Genre;
import com.example.homework7_datajpa.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService{
    private final GenreRepository repository;

    public GenreServiceImpl(@Autowired GenreRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Genre> getAll() {
        return repository.findAll();
    }

    @Override
    public Genre getById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Optional<Genre> getByName(String name) {
        return repository.findByName(name);
    }

    @Override
    @Transactional
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void insert(Genre genre) {
        repository.save(genre);
    }
}
