package ru.otus.spring.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.rest.dto.resposne.GenreBookResponseDto;
import ru.otus.spring.service.GenreService;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/{bookId}")
    public List<GenreBookResponseDto> getAllGenresByBookId(@PathVariable("bookId") long bookId) {
        return genreService.getAllGenresByBookId(bookId);
    }
}
