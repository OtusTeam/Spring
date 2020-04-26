package ru.otus.spring.rest;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Genre;
import ru.otus.spring.service.AuthorService;
import ru.otus.spring.service.GenreService;

@Controller
public class GenreController {
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/genres")
    public String listPage(Model model) {
        List<Genre> genres = genreService.getAll();
        model.addAttribute("genres", genres);
        return "genreList";
    }

}
