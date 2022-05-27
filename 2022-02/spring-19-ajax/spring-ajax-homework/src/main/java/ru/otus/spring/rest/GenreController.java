package ru.otus.spring.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.rest.dto.resposne.GenreBookResponseDto;
import ru.otus.spring.service.GenreService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/getAllGenresByBookId")
    public List<GenreBookResponseDto> getAllGenresByBookId(@RequestParam("bookId") long bookId) {
        return genreService.getAllGenresByBookId(bookId);
    }
}
