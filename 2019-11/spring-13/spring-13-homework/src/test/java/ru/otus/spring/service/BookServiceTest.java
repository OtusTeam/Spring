package ru.otus.spring.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.spring.models.Author;
import ru.otus.spring.models.Book;
import ru.otus.spring.models.Genre;
import ru.otus.spring.repositories.AuthorRepository;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.repositories.CommentRepository;
import ru.otus.spring.repositories.GenreRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class BookServiceTest {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private GenreRepository genreRepository;
    private CommentRepository commentRepository;
    private BookService bookService;

    @Autowired
    public BookServiceTest(BookRepository bookRepository, AuthorRepository authorRepository,
                           GenreRepository genreRepository, CommentRepository commentRepository) {
        this.bookRepository = bookRepository;
        this.bookService = new BookService(bookRepository,
                new AuthorService(authorRepository), new GenreService(genreRepository),
                new CommentService(commentRepository, bookRepository));
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.commentRepository = commentRepository;
    }

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
        bookService.add("book1");
        bookService.add("book2");
        bookService.add("book3");

        authorRepository.deleteAll();
        authorRepository.save(new Author("author1"));
        bookService.bookAuthorAdd("book1", "author1");

        genreRepository.deleteAll();
        genreRepository.save(new Genre("genre1"));
        bookService.bookGenreAdd("book1", "genre1");
    }

    @Test
    void getAll() {
        assertThat(bookService.getAll().size()).isEqualTo(3);
    }

    @Test
    void findByCaption() {
        List<Book> books = bookService.findByCaption("book1");
        assertThat(books.size()).isEqualTo(1);
        assertThat(books.get(0).getId()).isNotEmpty();
        assertThat(books.get(0).getCaption()).isNotEmpty();
        assertThat(books.get(0).getCaption()).isEqualTo("book1");
    }

    @Test
    void add() {
        bookService.add("book4");
        assertThat(bookService.getAll().size()).isEqualTo(4);
    }

    @Test
    void deleteBookByCaption() {
        bookService.deleteBookByCaption("book1");
        assertThat(bookService.getAll().size()).isEqualTo(2);
        assertThat(
                bookService.getAll().stream()
                        .map(Book::getCaption)
                        .collect(Collectors.toList())
        ).containsExactlyInAnyOrder("book2", "book3");
    }

    @Test
    void bookAuthorAdd() {
        authorRepository.save(new Author("author2"));
        bookService.bookAuthorAdd("book1", "author2");

        List<Book> books = bookService.findByCaption("book1");
        assertThat(books.size()).isEqualTo(1);
        Book book = books.get(0);
        assertThat(book.getCaption()).isEqualTo("book1");

        Set<Author> bookAuthors = book.getAuthors();
        assertThat(
                bookAuthors.stream()
                        .map(Author::getName)
                        .collect(Collectors.toList())
        ).containsExactlyInAnyOrder("author1", "author2");
    }

    @Test
    void bookAuthorDelete() {
        bookService.bookAuthorDelete("book1", "author1");
        List<Book> books = bookService.findByCaption("book1");
        assertThat(books.size()).isEqualTo(1);
        Book book = books.get(0);
        assertThat(book.getAuthors().size()).isEqualTo(0);
    }

    @Test
    void bookGenreAdd() {
        genreRepository.save(new Genre("genre2"));
        bookService.bookGenreAdd("book1", "genre2");

        List<Book> books = bookService.findByCaption("book1");
        assertThat(books.size()).isEqualTo(1);

        Book book = books.get(0);
        assertThat(book.getCaption()).isEqualTo("book1");

        Set<Genre> bookGenres = book.getGenres();
        assertThat(
                bookGenres.stream()
                        .map(Genre::getName)
                        .collect(Collectors.toList())
        ).containsExactlyInAnyOrder("genre1", "genre2");
    }

    @Test
    void bookGenreDelete() {
        bookService.bookGenreDelete("book1", "genre1");
        List<Book> books = bookService.findByCaption("book1");
        assertThat(books.size()).isEqualTo(1);
        Book book = books.get(0);
        assertThat(book.getGenres().size()).isEqualTo(0);
    }
}
