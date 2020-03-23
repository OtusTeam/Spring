package ru.otus.spring.repositories;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.spring.models.Author;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class AuthorRepositoryTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private AuthorRepository authorRepository;

    @BeforeEach
    void setUp() {
        authorRepository.deleteAll();
    }

    @Test
    void testAuthorSave() {
        Author author = new Author("author1");
        authorRepository.save(author);
        assertThat(author.getId()).isNotNull().isNotEmpty();
    }

    @Test
    void findAll() {
        authorRepository.save(new Author("author1"));
        authorRepository.save(new Author("author2"));
        authorRepository.save(new Author("author3"));
        List<Author> authors = mongoTemplate.findAll(Author.class);
        assertThat(authors.size()).isEqualTo(3);
    }
}
