package com.example.homework6_jpa.service;

import com.example.homework6_jpa.dao.GenreDao;
import com.example.homework6_jpa.domain.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService{
    private final GenreDao repository;

    public GenreServiceImpl(@Autowired GenreDao repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Genre getById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> getByName(String name) {
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
