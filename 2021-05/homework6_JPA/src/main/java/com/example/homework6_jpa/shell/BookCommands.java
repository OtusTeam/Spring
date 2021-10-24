package com.example.homework6_jpa.shell;

import com.example.homework6_jpa.domain.Author;
import com.example.homework6_jpa.domain.Genre;
import com.example.homework6_jpa.service.AuthorService;
import com.example.homework6_jpa.service.BookService;
import com.example.homework6_jpa.service.GenreService;
import com.example.homework6_jpa.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;

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

    @ShellMethod(value = "find book by name", key = {"findBookByName"})
    public String findBookByName(@ShellOption String name) { return Util.listToString(service.getByName(name));}

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
            List<Author> authors = authorService.getByName(authorName);
            List<Genre> genre = genreService.getByName(genreName);
            return String.format("Book %s is added", service.save(name, authors.get(0).getId(), genre.get(0).getId()));
        } catch (EmptyResultDataAccessException exp) {
            return "There is no such author or genre " + exp.getRootCause();
        }
    }

    @ShellMethod(value = "add book", key = {"addBookId"})
    public String addBook(@ShellOption String name, @ShellOption long authorId, @ShellOption long genreId) {
        try {
            return String.format("Book %s is added", service.save(name, authorId, genreId));
        } catch (EmptyResultDataAccessException exp) {
            return "There is no such author or genre " + exp.getRootCause();
        }
    }
}
