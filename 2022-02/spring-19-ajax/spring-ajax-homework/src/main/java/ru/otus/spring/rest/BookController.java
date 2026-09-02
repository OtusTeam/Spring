package ru.otus.spring.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.rest.dto.request.ChangeBookInfoRequestDto;
import ru.otus.spring.rest.dto.request.CreateFullBookInfoRequestDto;
import ru.otus.spring.rest.dto.resposne.FullBookInfoResponseDto;
import ru.otus.spring.rest.dto.resposne.SimpleBookInfoResponseDto;
import ru.otus.spring.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/")
    public List<SimpleBookInfoResponseDto> getAllBooks() {
        return bookService.getSimpleBookInfoList();
    }

    @GetMapping("/{id}")
    public SimpleBookInfoResponseDto getBookById(@PathVariable("id") long id) {
        return bookService.getSimpleBookInfoById(id);
    }

    @PostMapping("/")
    public void addNewBook(@RequestBody CreateFullBookInfoRequestDto dto) {
        bookService.addNewBook(dto);
    }

    @PutMapping("/{id}")
    public void changeBookName(@PathVariable("id") long id, @RequestBody ChangeBookInfoRequestDto dto) {
        bookService.changeBookName(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable("id") long id) {
        bookService.deleteBookById(id);
    }
}
