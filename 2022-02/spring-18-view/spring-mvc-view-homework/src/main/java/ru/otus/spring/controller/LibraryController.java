package ru.otus.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.dto.request.CreateFullBookInfoRequestDto;
import ru.otus.spring.dto.resposne.FullBookInfoResponseDto;
import ru.otus.spring.dto.request.ChangeBookInfoRequestDto;
import ru.otus.spring.service.AuthorService;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.CommentService;
import ru.otus.spring.service.GenreService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class LibraryController {

    private final BookService bookService;
    private final CommentService commentService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @GetMapping("/")
    public String listPage(Model model) {
        List<FullBookInfoResponseDto> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/book/edit")
    public String editBookPage(@RequestParam("id") int id, Model model) {
        FullBookInfoResponseDto book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "editBook";
    }

    @Validated
    @PostMapping("/book/edit")
    public String changeBookName(@Valid @ModelAttribute("book") ChangeBookInfoRequestDto dto,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "editBook";
        }

        bookService.changeBookName(dto);
        return "redirect:/";
    }

    @GetMapping("/book/addNew")
    public String addNewBookPage(Model model) {
        model.addAttribute("book", new CreateFullBookInfoRequestDto());
        return "addNewBook";
    }

    @Validated
    @PostMapping("/book/addNew")
    public String addNewBook(@Valid @ModelAttribute("book") CreateFullBookInfoRequestDto dto,
                                 BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "addNewBook";
        }

        bookService.addNewBook(dto);
        return "redirect:/";
    }

    @GetMapping("/book/delete")
    public String deleteBook(@RequestParam("id") int id, Model model) {
        bookService.deleteBookById(id);
        return "redirect:/";
    }

    @GetMapping("/book/comments")
    public String bookCommentsPage(@RequestParam("id") int id, Model model) {
        model.addAttribute("comments", commentService.getAllCommentsByBookId(id));
        return "bookComments";
    }

    @GetMapping("/book/authors")
    public String bookAuthorsPage(@RequestParam("id") int id, Model model) {
        model.addAttribute("authors", authorService.getAllAuthorsByBookId(id));
        return "bookAuthors";
    }

    @GetMapping("/book/genres")
    public String bookGenresPage(@RequestParam("id") int id, Model model) {
        model.addAttribute("genres", genreService.getAllGenresByBookId(id));
        return "bookGenres";
    }

}
