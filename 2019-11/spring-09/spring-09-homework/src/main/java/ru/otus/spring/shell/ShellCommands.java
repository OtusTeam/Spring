package ru.otus.spring.shell;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.models.Author;
import ru.otus.spring.models.Book;
import ru.otus.spring.models.Comment;
import ru.otus.spring.models.Genre;
import ru.otus.spring.repositories.AuthorRepositoryJpaImpl;
import ru.otus.spring.repositories.BookRepositoryJpaImpl;
import ru.otus.spring.repositories.CommentRepositoryJpaImpl;
import ru.otus.spring.repositories.GenreRepositoryJpaImpl;
import ru.otus.spring.service.PresentationService;

@ShellComponent
public class ShellCommands {
    private final AuthorRepositoryJpaImpl authorRepository;
    private final BookRepositoryJpaImpl bookRepository;
    private final CommentRepositoryJpaImpl commentRepository;
    private final GenreRepositoryJpaImpl genreRepository;
    private final PresentationService converter;

    @Autowired
    public ShellCommands(AuthorRepositoryJpaImpl authorRepository, BookRepositoryJpaImpl bookRepository,
                         CommentRepositoryJpaImpl commentRepository, GenreRepositoryJpaImpl genreRepository,
                         PresentationService converter) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
        this.genreRepository = genreRepository;
        this.converter = converter;
    }

    @ShellMethod(value = "get books list (no args)", key = {"b"})
    public List<String> getBooks() {
        List<Book> books = bookRepository.findAll();
        return converter
                .convert(books, b -> String.format("Book Id: %s -> Caption: %s", b.getId(), b.getCaption()));
    }

    @ShellMethod(value = "add book (caption)", key = {"ba"})
    public void addBook(@ShellOption() String caption) {
        Book book = new Book();
        book.setCaption(caption);
        bookRepository.save(book);
    }

    @ShellMethod(value = "delete book by id (id)", key = {"bd"})
    public void deleteBook(@ShellOption() long id) {
        bookRepository.deleteById(id);
    }

    @ShellMethod(value = "get book info by id (id)", key = {"bi"})
    public Book bookInfo(@ShellOption() long id) {
        return bookRepository.findById(id).orElseThrow();
    }

    @ShellMethod(value = "add book's author by id (bookId, authorId)", key = {"baa"})
    public void bookAuthorAdd(@ShellOption() long bookId, @ShellOption() long authorId) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        List<Author> authors = book.getAuthors();
        authors.add(authorRepository.findById(authorId).orElseThrow());
        book.setAuthors(authors);
        bookRepository.save(book);
    }

    @ShellMethod(value = "delete book's author by id (bookId, authorId)", key = {"bad"})
    public void bookAuthorDelete(@ShellOption() long bookId, @ShellOption() long authorId) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        List<Author> authors = book.getAuthors();
        book.setAuthors(authors.stream().filter(a -> a.getId() != authorId).collect(Collectors.toList()));
        bookRepository.save(book);
    }

    @ShellMethod(value = "add book's genre by id (bookId, genreId)", key = {"bga"})
    public void bookGenreAdd(@ShellOption() long bookId, @ShellOption() long genreId) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        List<Genre> genres = book.getGenres();
        genres.add(genreRepository.findById(genreId).orElseThrow());
        book.setGenres(genres);
        bookRepository.save(book);
    }

    @ShellMethod(value = "delete book's genre by id (bookId, genreId)", key = {"bgd"})
    public void bookGenreDelete(@ShellOption() long bookId, @ShellOption() long genreId) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        List<Genre> genres = book.getGenres();
        book.setGenres(genres.stream().filter(a -> a.getId() != genreId).collect(Collectors.toList()));
        bookRepository.save(book);
    }

    @ShellMethod(value = "get authors list", key = {"a"})
    public List<String> getAuthors() {
        List<Author> authors = authorRepository.findAll();
        return converter.convert(authors, a -> String.format("Author Id: %s, Name: %s", a.getId(), a.getName()));
    }

    @ShellMethod(value = "add author (name)", key = {"aa"})
    public void addAuthor(@ShellOption() String name) {
        authorRepository.save(new Author(0L, name));
    }

    @ShellMethod(value = "delete author by id (authorId)", key = {"ad"})
    public void deleteAuthor(@ShellOption() long authorId) {
        authorRepository.deleteById(authorId);
    }

    @ShellMethod(value = "get genres list", key = {"g"})
    public List<String> getGenres() {
        List<Genre> genres =  genreRepository.findAll();
        return converter.convert(genres, g -> String.format("Genre Id: %s, Name: %s", g.getId(), g.getName()));
    }

    @ShellMethod(value = "add genre (name)", key = {"ga"})
    public void addGenre(@ShellOption() String name) {
        genreRepository.save(new Genre(0L, name));
    }

    @ShellMethod(value = "delete genre by id (genreId)", key = {"gd"})
    public void deleteGenre(@ShellOption() long genreId) {
        genreRepository.deleteById(genreId);
    }

    @ShellMethod(value = "get all comment list", key = {"c"})
    public List<String> getComments() {
        List<Comment> comments = commentRepository.findAll();
        return converter
                .convert(
                        comments, b -> String.format("Book Id: %s, Comment Id: %s, Comment: %s",
                                b.getId(), b.getBook().getId(), b.getComment())
                );
    }

    @ShellMethod(value = "get book comment list (bookId)", key = {"cb"})
    public List<String> getBookComments(@ShellOption() long bookId) {
        List<Comment> comments = commentRepository.findByBookId(bookId);
        return converter.convert(comments, b -> String.format("Comment Id: %s, Comment: %s", b.getId(),
                b.getComment()));
    }

    @ShellMethod(value = "add comment (bookId, text)", key = {"ca"})
    public void addComment(@ShellOption() long bookId, @ShellOption() String text) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        commentRepository.save(new Comment(0L, book, text));
    }

    @ShellMethod(value = "delete comment by id (commentId)", key = {"cd"})
    public void deleteComment(@ShellOption() long commentId) {
        commentRepository.deleteById(commentId);
    }
}
