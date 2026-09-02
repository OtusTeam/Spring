package ru.otus.spring.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookPageController {

    @GetMapping("/")
    public String booksPage(Model model) {
        return "books";
    }

    @GetMapping("/book/comments")
    public String bookComments(@RequestParam("id") long id, Model model) {
        return "bookComments";
    }

    @GetMapping("/book/authors")
    public String bookAuthors(@RequestParam("id") long id, Model model) {
        return "bookAuthors";
    }

    @GetMapping("/book/genres")
    public String bookGenres(@RequestParam("id") long id, Model model) {
        return "bookGenres";
    }

    @GetMapping("/book/edit")
    public String bookEdit(@RequestParam("id") long id, Model model) {
        return "editBook";
    }

    @GetMapping("/book/addNew")
    public String addNewBook(Model model) {
        return "addNewBook";
    }

    @GetMapping("/book/delete")
    public String bookDelete(@RequestParam("id") String id, @RequestParam("name") String name, Model model) {
        return "deleteBook";
    }
}
