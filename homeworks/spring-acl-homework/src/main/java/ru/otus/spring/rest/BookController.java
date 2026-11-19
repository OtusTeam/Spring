package ru.otus.spring.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.rest.dto.request.ChangeBookInfoRequestDto;
import ru.otus.spring.rest.dto.request.CreateFullBookInfoRequestDto;
import ru.otus.spring.rest.dto.resposne.AuthorBookResponseDto;
import ru.otus.spring.rest.dto.resposne.CommentBookResponseDto;
import ru.otus.spring.rest.dto.resposne.GenreBookResponseDto;
import ru.otus.spring.rest.dto.resposne.SimpleBookInfoResponseDto;
import ru.otus.spring.service.AuthorService;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.CommentService;
import ru.otus.spring.service.GenreService;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final CommentService commentService;
    private final AuthorService authorService;
    private final GenreService genreService;

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
        bookService.changeBookName(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable("id") long id) {
        bookService.deleteBookById(id);
    }

    @GetMapping("/{id}/comments")
    public List<CommentBookResponseDto> getAllCommentsByBookId(@PathVariable("id") long bookId) {
        return commentService.getAllCommentsByBookId(bookId);
    }

    @GetMapping("/{id}/authors")
    public List<AuthorBookResponseDto> getAllAuthorsByBookId(@PathVariable("id") long bookId) {
        return authorService.getAllAuthorsByBookId(bookId);
    }

    @GetMapping("/{id}/genres")
    public List<GenreBookResponseDto> getAllGenresByBookId(@PathVariable("id") long bookId) {
        return genreService.getAllGenresByBookId(bookId);
    }
}
