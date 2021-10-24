package com.example.homework5_jdbc.service;

import com.example.homework5_jdbc.dao.AuthorDao;
import com.example.homework5_jdbc.domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService{
    private final AuthorDao repository;

    public AuthorServiceImpl(@Autowired AuthorDao repository) {
        this.repository = repository;
    }

    @Override
    public List<Author> getAll() {
        return repository.getAll();
    }

    @Override
    public Author getById(long id) {
        return repository.getById(id);
    }

    @Override
    public Author getByName(String name) {
        return repository.getByName(name);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public void insert(Author author) {
        repository.insert(author);
    }
}
