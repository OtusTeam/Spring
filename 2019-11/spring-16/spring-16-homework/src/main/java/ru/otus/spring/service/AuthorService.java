package ru.otus.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.model.Author;
import ru.otus.spring.repository.AuthorRepositoryJpa;

@Service
public class AuthorService {
    private AuthorRepositoryJpa authorRepositoryJpa;

    @Autowired
    public AuthorService(AuthorRepositoryJpa authorRepositoryJpa) {
        this.authorRepositoryJpa = authorRepositoryJpa;
    }

    public List<Author> getAll() {
        return authorRepositoryJpa.findAll();
    }

    public Optional<Author> findById(long id) {
        return authorRepositoryJpa.findById(id);
    }

    public void add(String name) {
        authorRepositoryJpa.save(new Author(0L, name));
    }

    public void deleteById(long id) {
        authorRepositoryJpa.deleteById(id);
    }

}
