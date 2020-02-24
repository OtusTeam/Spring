package ru.otus.work.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.work.domain.Book;
import ru.otus.work.repositories.BookRepository;

@RestController
@AllArgsConstructor
public class BookController {

    private final BookRepository bookRepository;

    @GetMapping("/api/books")
    public Flux<Book> getAllBook() {
        return bookRepository.findAll();
    }

    @GetMapping("/api/books/{id}")
    public Mono<Book> getBookById(
            @PathVariable String id
    ) {
        return bookRepository.findById(id);
    }

    @PostMapping("/api/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Book> saveBook(
            @RequestBody
                    Book book
    ) {
        return bookRepository.save(
                book
        );
    }

    @PutMapping("/api/books")
    public Mono<Book> modifyBook(
            @RequestBody
                    Book book
    ) {
        return bookRepository.save(
                book
        );
    }

    @DeleteMapping("/api/books/{id}")
    public Mono<Void> deleteBook(
            @PathVariable String id
    ) {
        return bookRepository.deleteById(id);
    }
}
