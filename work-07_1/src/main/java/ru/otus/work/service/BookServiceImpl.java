package ru.otus.work.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.work.domain.Author;
import ru.otus.work.domain.Book;
import ru.otus.work.domain.CommentBook;
import ru.otus.work.domain.Genre;
import ru.otus.work.repositories.BookJpa;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookJpa bookJpa;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final CommentBookService commentBookService;

    public BookServiceImpl(BookJpa bookJpa, AuthorService authorService, GenreService genreService, CommentBookService commentBookService) {
        this.bookJpa = bookJpa;
        this.authorService = authorService;
        this.genreService = genreService;
        this.commentBookService = commentBookService;
    }

    @Override
    public void save(String name,
                     String authorStr,
                     String genreStr,
                     String description) {
        Author author = null;
        if (authorStr != null && authorStr.length() > 0) {
            author = Author.builder()
                    .name(authorStr)
                    .build();
        }

        Genre genre = null;
        if (genreStr != null && genreStr.length() > 0) {
            genre = Genre.builder()
                    .name(genreStr)
                    .build();
        }

        Book book = Book.builder()
                .name(name)
                .author(author)
                .genre(genre)
                .description(description)
                .build();

        authorService.save(book.getAuthor());
        genreService.save(book.getGenre());
        bookJpa.save(book);
    }

    @Override
    public void modify(String number,
                       String name,
                       String authorStr,
                       String genreStr,
                       String description) {
        Author author = null;
        if (authorStr != null && authorStr.length() > 0) {
            author = Author.builder()
                    .name(authorStr)
                    .build();
        }

        Genre genre = null;
        if (genreStr != null && genreStr.length() > 0) {
            genre = Genre.builder()
                    .name(genreStr)
                    .build();
        }

        Book book = Book.builder()
                .id(Long.valueOf(number))
                .name(name)
                .author(author)
                .genre(genre)
                .description(description)
                .build();

        book.setAuthor(authorService.save(book.getAuthor()));
        book.setGenre(genreService.save(book.getGenre()));
        bookJpa.save(book);
    }

    @Override
    public void delete(Long id) {
        bookJpa.deleteById(id);
    }

    @Override
    public List<Book> listAll() {
        return bookJpa.getAll();
    }

    @Override
    public Book findById(Long id) {
        return bookJpa.getById(id).orElse(null);
    }

    @Override
    public void addComment(Long bookId, String comment) {
        CommentBook commentBook = CommentBook.builder()
                .bookId(bookId)
                .text(comment)
                .build();

        commentBookService.save(commentBook);
    }
}
