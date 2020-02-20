package ru.otus.spring.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.models.Author;
import ru.otus.spring.models.Book;
import ru.otus.spring.models.Comment;
import ru.otus.spring.models.Genre;
import ru.otus.spring.repositories.BookRepository;

@Service
public class BookService {
    private BookRepository bookRepository;
    private AuthorService authorService;
    private GenreService genreService;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorService authorService, GenreService genreService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @Transactional
    public List<String> getAll() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(b -> String.format("%s - %s", b.getId(), b.getCaption()))
                .collect(Collectors.toList());
    }

    @Transactional
    public String bookInfoById(long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) {
            return "";
        }
        List<Comment> comments = book.getComments();
        List<Author> authors = book.getAuthors();
        List<Genre> genres = book.getGenres();
        StringBuilder sb = new StringBuilder();
        sb.append(book.getId()).append(" - ")
                .append(book.getCaption());
        if (authors != null && !authors.isEmpty()) {
            sb.append(", ")
                    .append(
                            authors.stream().map(Author::getName).collect(Collectors.joining(", "))
                    );
        }
        sb.append(". ");
        if (genres != null && !genres.isEmpty()) {
            sb.append(genres.stream().map(Genre::getName).collect(Collectors.joining(", ")));
        }
        if (comments != null && !comments.isEmpty()) {
            sb.append("\nBook comments:\n")
                    .append(
                            comments.stream().map(Comment::getComment).collect(Collectors.joining("\n"))
                    );
        }
        return sb.toString();
    }

    public void add(String caption) {
        Book book = new Book();
        book.setId(0L);
        book.setCaption(caption);
        bookRepository.save(book);
    }

    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public void bookAuthorAdd(long bookId, long authorId) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        List<Author> authors = book.getAuthors();
        authors.add(authorService.findById(authorId).orElseThrow());
        book.setAuthors(authors);
        bookRepository.save(book);
    }

    @Transactional
    public void bookAuthorDelete(long bookId, long authorId) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        List<Author> authors = book.getAuthors();
        book.setAuthors(
                authors.stream()
                        .filter(a -> a.getId() != authorId)
                        .collect(Collectors.toList())
        );
        bookRepository.save(book);
    }

    @Transactional
    public void bookGenreAdd(long bookId, long genreId) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        List<Genre> genres = book.getGenres();
        genres.add(genreService.findById(genreId).orElseThrow());
        book.setGenres(genres);
        bookRepository.save(book);
    }

    @Transactional
    public void bookGenreDelete(long bookId, long genreId) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        List<Genre> genres = book.getGenres();
        book.setGenres(
                genres.stream()
                        .filter(a -> a.getId() != genreId)
                        .collect(Collectors.toList())
        );
        bookRepository.save(book);
    }
}
