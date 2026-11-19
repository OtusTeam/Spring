package ru.otus.spring.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.rest.dto.resposne.AuthorBookResponseDto;
import ru.otus.spring.service.AuthorService;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/{bookId}")
    public List<AuthorBookResponseDto> getAllAuthorsByBookId(@PathVariable("bookId") long bookId) {
        return authorService.getAllAuthorsByBookId(bookId);
    }
}
