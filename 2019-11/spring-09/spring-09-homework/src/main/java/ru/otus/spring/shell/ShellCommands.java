package ru.otus.spring.shell;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.service.AuthorService;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.CommentService;
import ru.otus.spring.service.GenreService;

@ShellComponent
public class ShellCommands {
    private final AuthorService authorService;
    private final BookService bookService;
    private final CommentService commentService;
    private final GenreService genreService;

    @Autowired
    public ShellCommands(AuthorService authorService, BookService bookService,
                         CommentService commentService, GenreService genreService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.commentService = commentService;
        this.genreService = genreService;
    }

    @ShellMethod(value = "get books list (no args)", key = {"b"})
    public List<String> getBooks() {
        return bookService.getAll();
    }

    @ShellMethod(value = "add book (caption)", key = {"ba"})
    public void addBook(@ShellOption() String caption) {
        bookService.add(caption);
    }

    @ShellMethod(value = "delete book by id (id)", key = {"bd"})
    public void deleteBook(@ShellOption() long id) {
        bookService.deleteById(id);
    }

    @ShellMethod(value = "get book info by id (id)", key = {"bi"})
    public String bookInfo(@ShellOption() long id) {
        return bookService.bookInfoById(id);
    }

    @ShellMethod(value = "add book's author by id (bookId, authorId)", key = {"baa"})
    public void bookAuthorAdd(@ShellOption() long bookId, @ShellOption() long authorId) {
        bookService.bookAuthorAdd(bookId, authorId);
    }

    @ShellMethod(value = "delete book's author by id (bookId, authorId)", key = {"bad"})
    public void bookAuthorDelete(@ShellOption() long bookId, @ShellOption() long authorId) {
        bookService.bookAuthorDelete(bookId, authorId);
    }

    @ShellMethod(value = "add book's genre by id (bookId, genreId)", key = {"bga"})
    public void bookGenreAdd(@ShellOption() long bookId, @ShellOption() long genreId) {
        bookService.bookGenreAdd(bookId, genreId);
    }

    @ShellMethod(value = "delete book's genre by id (bookId, genreId)", key = {"bgd"})
    public void bookGenreDelete(@ShellOption() long bookId, @ShellOption() long genreId) {
        bookService.bookGenreDelete(bookId, genreId);
    }

    @ShellMethod(value = "get authors list", key = {"a"})
    public List<String> getAuthors() {
        return authorService.getAll();
    }

    @ShellMethod(value = "add author (name)", key = {"aa"})
    public void addAuthor(@ShellOption() String name) {
        authorService.add(name);
    }

    @ShellMethod(value = "delete author by id (authorId)", key = {"ad"})
    public void deleteAuthor(@ShellOption() long authorId) {
        authorService.deleteById(authorId);
    }

    @ShellMethod(value = "get genres list", key = {"g"})
    public List<String> getGenres() {
        return genreService.getAll();
    }

    @ShellMethod(value = "add genre (name)", key = {"ga"})
    public void addGenre(@ShellOption() String name) {
        genreService.add(name);
    }

    @ShellMethod(value = "delete genre by id (genreId)", key = {"gd"})
    public void deleteGenre(@ShellOption() long genreId) {
        genreService.deleteById(genreId);
    }

    @ShellMethod(value = "get all comment list", key = {"c"})
    public List<String> getComments() {
        return commentService.getAll();
    }

    @ShellMethod(value = "get book comment list (bookId)", key = {"cb"})
    public List<String> getBookComments(@ShellOption() long bookId) {
        return commentService.getBookComments(bookId);
    }

    @ShellMethod(value = "add comment (bookId, text)", key = {"ca"})
    public void addComment(@ShellOption() long bookId, @ShellOption() String text) {
        commentService.add(bookId, text);
    }

    @ShellMethod(value = "delete comment by id (commentId)", key = {"cd"})
    public void deleteComment(@ShellOption() long commentId) {
        commentService.deleteById(commentId);
    }
}
