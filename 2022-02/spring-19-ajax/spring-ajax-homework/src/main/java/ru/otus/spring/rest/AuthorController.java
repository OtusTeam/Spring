package ru.otus.spring.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.rest.dto.resposne.AuthorBookResponseDto;
import ru.otus.spring.service.AuthorService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/getAllAuthorsByBookId")
    public List<AuthorBookResponseDto> getAllAuthorsByBookId(@RequestParam("bookId") long bookId) {
        return authorService.getAllAuthorsByBookId(bookId);
    }
}
