package ru.otus.work.controller;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.work.actuators.IndexCounter;
import ru.otus.work.controller.dto.BookDto;
import ru.otus.work.service.BookService;

import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class BookController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final BookService bookService;
    private final IndexCounter indexCounter;

    @GetMapping("/")
    public String getAllBook(Model model) {
        model.addAttribute("books", bookService.listAll().stream().map(BookDto::toDto).collect(Collectors.toList()));
        LOGGER.debug(String.format("getAllBook: %s", model.asMap().get("books")));
        indexCounter.countedCall();
        return "books";
    }

    @GetMapping("/edit.html")
    public String getEditPage(Model model) {
        model.addAttribute("book", new BookDto(0L, "", "", "", ""));
        return "edit";
    }

    @GetMapping("/books")
    public String getBookById(
            @RequestParam("id") Long id,
            Model model
    ) {
        model.addAttribute("book", BookDto.toDto(bookService.findById(id)));
        LOGGER.debug(String.format("getBookById: %s", model.asMap().get("book")));
        return "edit";
    }

    @PostMapping(value = "/bookSave",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @Transactional
    public String saveBook(
            @ModelAttribute("book") BookDto book,
            Model model
    ) {
        if (book.getId() != null) {
            bookService.modify(
                    book.getId().toString(),
                    book.getName(),
                    book.getAuthor(),
                    book.getGenre(),
                    book.getDescription()
            );
        } else {
            bookService.save(
                    book.getName(),
                    book.getAuthor(),
                    book.getGenre(),
                    book.getDescription()
            );
        }

        model.addAttribute("books", bookService.listAll().stream().map(BookDto::toDto).collect(Collectors.toList()));
        LOGGER.debug(String.format("saveBook: %s", model.asMap().get("books")));
        return "books";
    }

    @GetMapping("/deletebooks")
    public String deleteBook(
            @RequestParam("id") Long id,
            Model model
    ) {
        bookService.delete(id);
        model.addAttribute("books", bookService.listAll().stream().map(BookDto::toDto).collect(Collectors.toList()));
        LOGGER.debug(String.format("deleteBook: %s", model.asMap().get("books")));
        return "books";
    }
}
