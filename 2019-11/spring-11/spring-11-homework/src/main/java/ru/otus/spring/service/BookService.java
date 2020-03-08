package ru.otus.spring.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.models.Author;
import ru.otus.spring.models.Book;
import ru.otus.spring.models.Genre;
import ru.otus.spring.repositories.BookRepositoryJpa;

@Service
public class BookService {
    private BookRepositoryJpa bookRepositoryJpa;
    private AuthorService authorService;
    private GenreService genreService;

    @Autowired
    public BookService(BookRepositoryJpa bookRepositoryJpa, AuthorService authorService, GenreService genreService) {
        this.bookRepositoryJpa = bookRepositoryJpa;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    public List<Book> getAll() {
        return bookRepositoryJpa.findAll();
    }

    public Optional<Book> bookInfoById(long id) {
        return bookRepositoryJpa.findWithLazyById(id);
    }

    public void add(String caption) {
        Book book = new Book(0, caption);
        bookRepositoryJpa.save(book);
    }

    public void deleteById(long id) {
        bookRepositoryJpa.deleteById(id);
    }

    @Transactional
    public void bookAuthorAdd(long bookId, long authorId) {
        Book book = bookRepositoryJpa.findById(bookId).orElseThrow();
        Set<Author> authors = book.getAuthors();
        authors.add(authorService.findById(authorId).orElseThrow());
        book.setAuthors(authors);
        bookRepositoryJpa.save(book);
    }

    @Transactional
    public void bookAuthorDelete(long bookId, long authorId) {
        Book book = bookRepositoryJpa.findById(bookId).orElseThrow();
        Set<Author> authors = book.getAuthors();
        book.setAuthors(
                authors.stream()
                        .filter(a -> a.getId() != authorId)
                        .collect(Collectors.toSet())
        );
        bookRepositoryJpa.save(book);
    }

    @Transactional
    public void bookGenreAdd(long bookId, long genreId) {
        Book book = bookRepositoryJpa.findById(bookId).orElseThrow();
        Set<Genre> genres = book.getGenres();
        genres.add(genreService.findById(genreId).orElseThrow());
        book.setGenres(genres);
        bookRepositoryJpa.save(book);
    }

    @Transactional
    public void bookGenreDelete(long bookId, long genreId) {
        Book book = bookRepositoryJpa.findById(bookId).orElseThrow();
        Set<Genre> genres = book.getGenres();
        book.setGenres(
                genres.stream()
                        .filter(a -> a.getId() != genreId)
                        .collect(Collectors.toSet())
        );
        bookRepositoryJpa.save(book);
    }
}
