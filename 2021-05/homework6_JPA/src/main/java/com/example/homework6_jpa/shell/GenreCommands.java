package com.example.homework6_jpa.shell;

import com.example.homework6_jpa.domain.Genre;
import com.example.homework6_jpa.service.GenreService;
import com.example.homework6_jpa.util.Util;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class GenreCommands {
    private final GenreService service;

    public GenreCommands(GenreService service) {
        this.service = service;
    }

    @ShellMethod(value = "find genre", key = {"findGenre"})
    public String findGenre(@ShellOption long id){
        return service.getById(id).toString();
    }

    @ShellMethod(value = "get all genres", key = {"listGenres"})
    public String findAllGenres(){
        return Util.listToString(service.getAll());
    }

    @ShellMethod(value = "delete genre", key = {"deleteGenre"})
    public String deleteGenre(@ShellOption long id) {
        service.delete(id);
        return String.format("Genre %s is deleted", id);
    }

    @ShellMethod(value = "add genre", key = {"addGenre"})
    public String addGenre(@ShellOption String name){
        Genre genre = new Genre(0, name);
        service.insert(genre);
        return String.format("Genre %s is added", genre.toString());
    }
}
