package com.example.homework7_datajpa.service;

import com.example.homework7_datajpa.model.Author;
import com.example.homework7_datajpa.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService{
    private final AuthorRepository repository;

    public AuthorServiceImpl(@Autowired AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Author> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Author> getById(long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Author> getByName(String name) {
        return repository.findByName(name);
    }

    @Override
    @Transactional
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void save(Author author) {
        repository.save(author);
    }
}
