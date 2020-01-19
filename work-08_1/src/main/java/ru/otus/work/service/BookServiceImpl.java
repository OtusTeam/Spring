package ru.otus.work.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.work.domain.Author;
import ru.otus.work.domain.Book;
import ru.otus.work.domain.CommentBook;
import ru.otus.work.domain.Genre;
import ru.otus.work.repositories.BookRepository;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final CommentBookService commentBookService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, GenreService genreService, CommentBookService commentBookService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.genreService = genreService;
        this.commentBookService = commentBookService;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(String name,
                     String authorStr,
                     String genreStr,
                     String description) {
        Author author = null;
        if (authorStr != null && authorStr.length() > 0) {
            List<Author> authors = authorService.findByName(authorStr);
            if (authors.isEmpty()) {
                author = Author.builder()
                        .name(authorStr)
                        .build();
            } else {
                author = authors.get(0);
            }
        }

        Genre genre = null;
        if (genreStr != null && genreStr.length() > 0) {
            List<Genre> genres = genreService.findByName(genreStr);
            if (genres.isEmpty()) {
                genre = Genre.builder()
                        .name(genreStr)
                        .build();
            } else {
                genre = genres.get(0);
            }
        }

        Book book = Book.builder()
                .name(name)
                .author(author)
                .genre(genre)
                .description(description)
                .build();

        bookRepository.save(book);
    }

    @Override
    public void modify(String number,
                       String name,
                       String authorStr,
                       String genreStr,
                       String description) {
        Author author = null;
        if (authorStr != null && authorStr.length() > 0) {

            List<Author> authors = authorService.findByName(authorStr);

            if (authors.isEmpty()) {
                author = Author.builder()
                        .name(authorStr)
                        .build();
            } else {
                author = authors.get(0);
            }
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

        //book.setAuthor(authorService.save(book.getAuthor()));
        book.setGenre(genreService.save(book.getGenre()));
        bookRepository.save(book);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
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
