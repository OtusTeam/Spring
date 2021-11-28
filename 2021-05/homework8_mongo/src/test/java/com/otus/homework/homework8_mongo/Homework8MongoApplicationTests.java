package com.otus.homework.homework8_mongo;

import com.otus.homework.homework8_mongo.domain.Author;
import com.otus.homework.homework8_mongo.domain.Book;
import com.otus.homework.homework8_mongo.domain.Genre;
import com.otus.homework.homework8_mongo.repository.AuthorRepository;
import com.otus.homework.homework8_mongo.repository.BookRepository;
import com.otus.homework.homework8_mongo.repository.GenreRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@DisplayName("Book operation testing")
@ComponentScan("com.otus.homework.homework8_mongo.repository, com.otus.homework.homework8_mongo.service")
class Homework8MongoApplicationTests {
    private static final String TEST_BOOK_TITLE = "Dandelion wine";
    private static final String TEST_AUTHOR_NAME = "Ray Bradbury";
    private static final String CHANGED_AUTHOR_NAME = "R.Bradbury";
    private static final String TEST_GENRE_NAME = "novel";
    private static final String CHANGED_GENRE_NAME = "NEW NOVEL";

    @Autowired
    private BookRepository repository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    @DisplayName("Правильно считает количество книг автора")
    void shouldReturnAuthorBooksCount() {
        Author author = authorRepository.findByName(TEST_AUTHOR_NAME).orElse(null);
        List<Book> books = repository.getAllByAuthor(author);
        long bookCount = authorRepository.getCountAuthorsBooks(author.getId());
        assertThat(books.size() == bookCount);

    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    @DisplayName("При изменении имени автора корректно изменяется имя автора в книгах")
    void shouldChangeAuthorInBook() {
        Author author = authorRepository.findByName(TEST_AUTHOR_NAME).orElse(null);
        author.setName(CHANGED_AUTHOR_NAME);
        authorRepository.saveWithBooks(author);
        List<Book> books = repository.getAllByAuthor(author);
        assertThat(books.get(0).getAuthor().getName() == CHANGED_AUTHOR_NAME);

    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    @DisplayName("При изменении наименования жанра корректно изменяется наименование жанра в книгах")
    void shouldChangeGenreInBooks() {
        Genre genre = genreRepository.findByName(TEST_GENRE_NAME).orElse(null);
        genre.setName(CHANGED_GENRE_NAME);
        genreRepository.saveWithBooks(genre);
        List<Book> books = repository.getAllByGenre(genre);
        assertThat(books.get(0).getGenre().getName() == CHANGED_GENRE_NAME);

    }
}
