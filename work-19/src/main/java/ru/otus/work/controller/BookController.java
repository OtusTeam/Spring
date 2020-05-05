package ru.otus.work.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.work.controller.dto.BookDto;
import ru.otus.work.service.BookService;

import java.util.Collections;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/")
    public String getAllBook(Model model) {
        model.addAttribute("books", bookService.listAll().stream().map(BookDto::toDto).collect(Collectors.toList()));
        return "books";
    }

    public String getAllBookFallback(Model model) {
        model.addAttribute("book",
                Collections.singletonList(
                        new BookDto(0L,
                                "Book",
                                "Book author",
                                "Book genre",
                                "Book description"
                        )
                )
        );
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
    ) throws InterruptedException {
        //TODO Эмитация "зависшего" метода если время выполнения > 300ms
        TimeUnit.MILLISECONDS.sleep(100L);
        model.addAttribute("book", BookDto.toDto(bookService.findById(id)));
        return "edit";
    }

    public String getBookByIdFallback(
            @RequestParam("id") Long id,
            Model model
    ) {
        model.addAttribute("book",
                new BookDto(0L,
                        "Book",
                        "Book author",
                        "Book genre",
                        "Book description"
                )
        );
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
        return "books";
    }

    @GetMapping("/deletebooks")
    public String deleteBook(
            @RequestParam("id") Long id,
            Model model
    ) {
        bookService.delete(id);
        model.addAttribute("books", bookService.listAll().stream().map(BookDto::toDto).collect(Collectors.toList()));
        return "books";
    }

    @GetMapping("/accessdenied")
    public String accessDenied(
            Model model
    ) {
        return "accessdenied";
    }
}
