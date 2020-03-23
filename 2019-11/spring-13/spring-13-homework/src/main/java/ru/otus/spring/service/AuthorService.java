package ru.otus.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.models.Author;
import ru.otus.spring.repositories.AuthorRepository;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    public List<Author> findByName(String name) {
        return authorRepository.findByName(name);
    }

    public void add(String name) {
        authorRepository.save(new Author(name));
    }

    public void deleteAuthorsByName(String name) {
        authorRepository.deleteAuthorsByName(name);
    }
}
