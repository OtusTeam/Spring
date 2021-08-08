package com.example.homework5_jdbc.service;

import com.example.homework5_jdbc.dao.GenreDao;
import com.example.homework5_jdbc.domain.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService{
    private final GenreDao repository;

    public GenreServiceImpl(@Autowired GenreDao repository) {
        this.repository = repository;
    }

    @Override
    public List<Genre> getAll() {
        return repository.getAll();
    }

    @Override
    public Genre getById(long id) {
        return repository.getById(id);
    }

    @Override
    public Genre getByName(String name) {
        return repository.getByName(name);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public void insert(Genre genre) {
        repository.insert(genre);

    }
}
