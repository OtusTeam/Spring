package ru.otus.work.service;

import org.springframework.stereotype.Service;
import ru.otus.work.domain.Author;
import ru.otus.work.repositories.AuthorJpa;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorJpa authorJpa;

    public AuthorServiceImpl(AuthorJpa authorJpa) {
        this.authorJpa = authorJpa;
    }

    @Override
    public Author save(Author author) {
        if (author == null) {
            return null;
        }

        List<Author> authors = authorJpa.getByName(author.getName());

        if (authors.isEmpty()) {
            author.setId(null);
           return authorJpa.save(author);
        } else {
           return authors.get(0);
        }
    }

    @Override
    public Author findById(Long id) {
        return authorJpa.getById(id).orElse(null);
    }
}
