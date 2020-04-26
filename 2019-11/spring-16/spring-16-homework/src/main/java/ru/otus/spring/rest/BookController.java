package ru.otus.spring.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.spring.exception.NotFoundException;
import ru.otus.spring.model.Book;
import ru.otus.spring.service.BookService;

@Controller
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String listBooks(Model model) {
        List<Book> books = bookService.getAll();
        model.addAttribute("books", books);
        return "bookIndex";
    }

    @GetMapping(value = "/edit/{id}")
    public String editBook(@PathVariable("id") Long id, Model model) {
        Book book = bookService.bookInfoById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("book", book);
        return "bookUpdate";
    }

    @GetMapping(value = "/info/{id}")
    public String infoBook(@PathVariable("id") Long id, Model model) {
        Book book = bookService.bookInfoById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("book", book);
        return "bookInfo";
    }

    @PostMapping(value = "/update/{id}")
    public String updateBook(@PathVariable("id") Long id, @Valid Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            book.setId(id);
            return "bookUpdate";
        }
        bookService.save(book);
        model.addAttribute( "books", bookService.getAll());
        return "bookIndex";
    }

    @GetMapping(value = "/fill")
    public String showForm(Book book) {
        return "bookAdd";
    }

    @PostMapping(value = "/add")
    public String addBook(@Valid Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "bookAdd";
        }

        bookService.save(book);
        model.addAttribute( "books", bookService.getAll());
        return "bookIndex";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") long id, Model model) {
        Book book = bookService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        bookService.deleteById(book.getId());
        model.addAttribute("books", bookService.getAll());
        return "bookIndex";
    }
}
