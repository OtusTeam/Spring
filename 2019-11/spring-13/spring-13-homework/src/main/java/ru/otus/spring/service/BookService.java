package ru.otus.spring.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.models.Author;
import ru.otus.spring.models.Book;
import ru.otus.spring.models.Genre;
import ru.otus.spring.repositories.BookRepository;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final CommentService commentService;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorService authorService, GenreService genreService,
                       CommentService commentService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.genreService = genreService;
        this.commentService = commentService;
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public List<Book> findByCaption(String caption) {
        return bookRepository.findByCaption(caption);
    }

    public void add(String caption) {
        Book book = new Book(caption);
        bookRepository.save(book);
    }

    @Transactional
    public void deleteBookByCaption(String caption) {
        bookRepository.findByCaption(caption)
                .forEach(commentService::deleteByBook);
        bookRepository.deleteBooksByCaption(caption);
    }

    @Transactional
    public void bookAuthorAdd(String bookCaption, String authorName) {
        Book book = bookRepository.findByCaption(bookCaption).stream()
                .findFirst().orElseThrow();
        Set<Author> authors = book.getAuthors();
        authors.add(authorService.findByName(authorName).stream().findFirst().orElseThrow());
        book.setAuthors(authors);
        bookRepository.save(book);
    }

    @Transactional
    public void bookAuthorDelete(String bookCaption, String authorName) {
        Book book = bookRepository.findByCaption(bookCaption).stream().findFirst().orElseThrow();
        Set<Author> authors = book.getAuthors();
        book.setAuthors(
                authors.stream()
                        .filter(a -> !a.getName().equals(authorName))
                        .collect(Collectors.toSet())
        );
        bookRepository.save(book);
    }

    @Transactional
    public void bookGenreAdd(String bookCaption, String genreName) {
        Book book = bookRepository.findByCaption(bookCaption).stream().findFirst().orElseThrow();
        Set<Genre> genres = book.getGenres();
        genres.add(genreService.findByName(genreName).stream().findFirst().orElseThrow());
        book.setGenres(genres);
        bookRepository.save(book);
    }

    @Transactional
    public void bookGenreDelete(String bookCaption, String genreName) {
        Book book = bookRepository.findByCaption(bookCaption).stream().findFirst().orElseThrow();
        Set<Genre> genres = book.getGenres();
        book.setGenres(
                genres.stream()
                        .filter(a -> !a.getName().equals(genreName))
                        .collect(Collectors.toSet())
        );
        bookRepository.save(book);
    }
}
