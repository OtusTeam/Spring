package ru.otus.spring.rest;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.spring.model.Author;
import ru.otus.spring.service.AuthorService;

@Controller
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public String listPage(Model model) {
        List<Author> authors = authorService.getAll();
        model.addAttribute("authors", authors);
        return "authorList";
    }

}
