package ru.otus.spring.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PersonPagesController {

    @GetMapping("/")
    public String listPage(Model model) {
        model.addAttribute("keywords", "list users in Omsk, omsk, list users, list users free");
        return "list";
    }
}
