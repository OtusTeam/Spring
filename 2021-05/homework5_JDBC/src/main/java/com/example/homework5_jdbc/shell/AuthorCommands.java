package com.example.homework5_jdbc.shell;

import com.example.homework5_jdbc.domain.Author;
import com.example.homework5_jdbc.service.AuthorService;
import com.example.homework5_jdbc.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class AuthorCommands {
    private final AuthorService service;

    public AuthorCommands(@Autowired AuthorService authorService) {
        this.service = authorService;
    }

    @ShellMethod(value = "find author by id", key = {"findAuthorId"})
    public String findAuthorById(@ShellOption long id){
        return service.getById(id).toString();
    }

    @ShellMethod(value = "find author by name", key = {"findAuthorName"})
    public String findAuthorByName(@ShellOption String name){
        return service.getByName(name).toString();
    }

    @ShellMethod(value = "get all authors", key = {"listAuthors"})
    public String findAllAuthors(){
        return Util.listToString(service.getAll());
    }

    @ShellMethod(value = "delete author", key = {"deleteAuthor"})
    public String deleteAuthor(@ShellOption long id) {
        service.delete(id);
        return String.format("Author %s is deleted", id);
    }

    @ShellMethod(value = "add author", key = {"addAuthor"})
    public String addAuthor(@ShellOption String name){
        Author author = new Author(0, name);
        service.insert(author);
        return String.format("Author %s is added", author.toString());
    }
}
