package ru.otus.spring.service;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.spring.models.Author;
import ru.otus.spring.repositories.AuthorRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class AuthorServiceTest {

    private AuthorRepository authorRepository;
    private AuthorService authorService;

    @Autowired
    public AuthorServiceTest(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
        this.authorService = new AuthorService(authorRepository);
    }

    @BeforeEach
    void setUp() {
        authorRepository.deleteAll();
        authorService.add("author1");
        authorService.add("author2");
        authorService.add("author3");
    }

    @Test
    void getAll() {
        assertThat(authorService.getAll().size()).isEqualTo(3);
    }

    @Test
    void findByName() {
        List<Author> authors = authorService.findByName("author1");
        assertThat(authors.size()).isEqualTo(1);
        assertThat(authors.get(0).getId()).isNotEmpty();
        assertThat(authors.get(0).getName()).isNotEmpty();
        assertThat(authors.get(0).getName()).isEqualTo("author1");
    }

    @Test
    void add() {
        authorService.add("author4");
        assertThat(authorService.getAll().size()).isEqualTo(4);
    }

    @Test
    void deleteAuthorsByName() {
        authorService.deleteAuthorsByName("author1");
        assertThat(authorService.getAll().size()).isEqualTo(2);
        assertThat(
                authorService.getAll().stream()
                        .map(Author::getName)
                        .collect(Collectors.toList())
        ).containsExactlyInAnyOrder("author2", "author3");
    }
}
