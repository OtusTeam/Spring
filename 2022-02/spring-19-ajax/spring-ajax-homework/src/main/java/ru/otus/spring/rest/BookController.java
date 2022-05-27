package ru.otus.spring.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.rest.dto.request.ChangeBookInfoRequestDto;
import ru.otus.spring.rest.dto.request.CreateFullBookInfoRequestDto;
import ru.otus.spring.rest.dto.resposne.FullBookInfoResponseDto;
import ru.otus.spring.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/getAllBooks")
    public List<FullBookInfoResponseDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/getBookById")
    public FullBookInfoResponseDto getBookById(@RequestParam("id") long id) {
        return bookService.getBookById(id);
    }

    @PostMapping("/addNewBook")
    public void addNewBook(@RequestBody CreateFullBookInfoRequestDto dto) {
        bookService.addNewBook(dto);
    }

    @PatchMapping("/changeBookName")
    public void changeBookName(@RequestBody ChangeBookInfoRequestDto dto) {
        bookService.changeBookName(dto);
    }

    @DeleteMapping("/deleteBookById")
    public void deleteBookById(@RequestParam("id") long id) {
        bookService.deleteBookById(id);
    }
}
