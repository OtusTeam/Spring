package com.example.homework5_jdbc.shell;

import com.example.homework5_jdbc.domain.Author;
import com.example.homework5_jdbc.domain.Book;
import com.example.homework5_jdbc.domain.Genre;
import com.example.homework5_jdbc.service.AuthorService;
import com.example.homework5_jdbc.service.BookService;
import com.example.homework5_jdbc.service.GenreService;
import com.example.homework5_jdbc.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class BookCommands {
    private final BookService service;
    private final AuthorService authorService;
    private final GenreService genreService;

    public BookCommands(@Autowired BookService service, @Autowired AuthorService authorService, @Autowired GenreService genreService) {
        this.service = service;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @ShellMethod(value = "find book", key = {"findBook"})
    public String findBook(@ShellOption long id) {
        return service.get(id).toString();
    }

    @ShellMethod(value = "get all books", key = {"listBooks"})
    public String findAllBooks() {
        return Util.listToString(service.getAll());
    }

    @ShellMethod(value = "delete book", key = {"deleteBook"})
    public String deleteBook(@ShellOption long id) {
        service.delete(id);
        return String.format("Book %s is deleted", id);
    }

    @ShellMethod(value = "add book", key = {"addBook"})
    public String addBook(@ShellOption String name, @ShellOption String authorName, @ShellOption String genreName) {
        try {
            return String.format("Book %s is added", service.insert(name, authorName, genreName));
        } catch (EmptyResultDataAccessException exp) {
            return "There is no such author or genre " + exp.getRootCause();
        }
    }

    @ShellMethod(value = "add book", key = {"addBookId"})
    public String addBook(@ShellOption String name, @ShellOption long authorId, @ShellOption long genreId) {
        try {
            return String.format("Book %s is added", service.insert(name, authorId, genreId));
        } catch (EmptyResultDataAccessException exp) {
            return "There is no such author or genre " + exp.getRootCause();
        }
    }
}
