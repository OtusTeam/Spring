package ru.otus.work.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookPagesController {

    @GetMapping("/")
    public String booksPage(Model model) {
        model.addAttribute("message", "All books");
        return "books";
    }
}
