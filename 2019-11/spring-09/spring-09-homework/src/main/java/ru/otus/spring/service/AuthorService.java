package ru.otus.spring.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<String> getAll() {
        return authorRepository.findAll().stream()
                .map(a -> String.format("%s - %s", a.getId(), a.getName()))
                .collect(Collectors.toList());
    }

    public Optional<Author> findById(long id) { return authorRepository.findById(id);}

    public void add(String name) {
        authorRepository.save(new Author(0L, name));
    }

    public void deleteById(long id) {
        authorRepository.deleteById(id);
    }

}
