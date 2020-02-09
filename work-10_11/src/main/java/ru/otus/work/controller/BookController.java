package ru.otus.work.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.otus.work.controller.dto.BookDto;
import ru.otus.work.service.BookService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/api/books")
    public ResponseEntity<List<BookDto>> getAllBook() {
        return ResponseEntity.ok()
                .body(bookService.listAll().stream().map(BookDto::toDto).collect(Collectors.toList()));
    }

    @GetMapping("/api/books/{id}")
    public ResponseEntity<BookDto> getBookById(
            @PathVariable Long id
    ) {
        if (id != null) {
            return ResponseEntity.ok().body(BookDto.toDto(bookService.findById(id)));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/books")
    @Transactional
    public ResponseEntity<String> saveBook(
            @RequestBody
                    BookDto bookDto
    ) {
        bookService.save(
                bookDto.getName(),
                bookDto.getAuthor(),
                bookDto.getGenre(),
                bookDto.getDescription()
        );

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/api/books")
    @Transactional
    public ResponseEntity<String> modifyBook(
            @RequestBody
                    BookDto bookDto
    ) {
        bookService.modify(
                bookDto.getId().toString(),
                bookDto.getName(),
                bookDto.getAuthor(),
                bookDto.getGenre(),
                bookDto.getDescription()
        );

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<String> deleteBook(
            @PathVariable Long id
    ) {
        if (id != null) {
            bookService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
