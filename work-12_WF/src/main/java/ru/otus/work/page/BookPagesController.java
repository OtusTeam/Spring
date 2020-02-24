package ru.otus.work.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.work.domain.Author;
import ru.otus.work.domain.Book;
import ru.otus.work.domain.Genre;
import ru.otus.work.repositories.BookRepository;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Date;

@Controller
public class BookPagesController {

    @GetMapping("/")
    public String booksPage(Model model) {
        model.addAttribute("message", "All books");
        return "books";
    }
}
