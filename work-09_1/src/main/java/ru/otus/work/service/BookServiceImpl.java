package ru.otus.work.service;

import org.springframework.stereotype.Service;
import ru.otus.work.domain.Author;
import ru.otus.work.domain.Book;
import ru.otus.work.domain.CommentBook;
import ru.otus.work.domain.Genre;
import ru.otus.work.repositories.BookRepository;
import ru.otus.work.repositories.BookRepositoryCustom;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookRepositoryCustom bookRepositoryCustom;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final CommentBookService commentBookService;

    public BookServiceImpl(BookRepository bookRepository, BookRepositoryCustom bookRepositoryCustom, AuthorService authorService, GenreService genreService, CommentBookService commentBookService) {
        this.bookRepository = bookRepository;
        this.bookRepositoryCustom = bookRepositoryCustom;
        this.authorService = authorService;
        this.genreService = genreService;
        this.commentBookService = commentBookService;
    }

    @Override
    public void save(String id,
                     String name,
                     List<String> authorsStr,
                     String genreStr,
                     String description) {
        List<Author> authors = new ArrayList<>();
        if (authorsStr != null && authorsStr.size() > 0) {
            for (String authorStr : authorsStr) {
                List<Author> authorsFind = authorService.findByName(authorStr);
                if (authorsFind.isEmpty()) {
                    Author author = Author.builder()
                            .name(authorStr)
                            .build();
                    authors.add(authorService.save(author));
                } else {
                    authors = authorsFind;
                }
            }
        }

        Genre genre = null;
        if (genreStr != null && genreStr.length() > 0) {
            List<Genre> genres = genreService.findByName(genreStr);
            if (genres.isEmpty()) {
                genre = Genre.builder()
                        .name(genreStr)
                        .build();
                genre = genreService.save(genre);
            } else {
                genre = genres.get(0);
            }
        }

        Book book = Book.builder()
                .id(id)
                .name(name)
                .authors(authors)
                .genre(genre)
                .description(description)
                .build();

        bookRepository.save(book);
    }

    @Override
    public void delete(String id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(String id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return bookRepositoryCustom.findByAuthor(author);
    }

    @Override
    public void addComment(String bookId, String comment) {
        CommentBook commentBook = CommentBook.builder()
                .bookId(bookId)
                .text(comment)
                .build();

        commentBookService.save(commentBook);
    }
}
