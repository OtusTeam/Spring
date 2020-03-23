package ru.otus.spring.repositories;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.spring.models.Book;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class BookRepositoryTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
    }

    @Test
    void testGenreSave() {
        Book book = new Book("book1");
        bookRepository.save(book);
        assertThat(book.getId()).isNotNull().isNotEmpty();
    }

    @Test
    void findAll() {
        bookRepository.save(new Book("book1"));
        bookRepository.save(new Book("book2"));
        bookRepository.save(new Book("book3"));
        List<Book> books = mongoTemplate.findAll(Book.class);
        assertThat(books.size()).isEqualTo(3);
    }
}
