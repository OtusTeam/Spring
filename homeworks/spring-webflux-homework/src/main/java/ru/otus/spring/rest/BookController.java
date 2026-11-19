package ru.otus.spring.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repositories.BookReactiveRepository;
import ru.otus.spring.rest.dto.request.ChangeBookInfoRequestDto;
import ru.otus.spring.rest.dto.request.CreateFullBookInfoRequestDto;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookReactiveRepository repository;

    @GetMapping("/")
    public Flux<Book> getAllBooks() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Book>> getBookById(@PathVariable("id") String id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @PostMapping("/")
    public Mono<Book> saveBook(@RequestBody CreateFullBookInfoRequestDto dto) {
        return repository.save(new Book(dto));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Book>> changeBookName(@PathVariable("id") String id, @RequestBody ChangeBookInfoRequestDto dto) {
        return repository.findById(id)
                .flatMap(book -> {
                    book.setName(dto.getName());
                    return repository.save(book);
                })
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteBookById(@PathVariable("id") String id) {
        return repository.deleteById(id);
    }

    @GetMapping("/comments/{bookId}")
    public Mono<ResponseEntity<List<Comment>>> getAllCommentsByBookId(@PathVariable("bookId") String bookId) {
        return repository.findById(bookId)
                .flatMap(book -> Mono.just(book.getComments()))
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @GetMapping("/authors/{bookId}")
    public Mono<ResponseEntity<List<Author>>> getAllAuthorsByBookId(@PathVariable("bookId") String bookId) {
        return repository.findById(bookId)
                .flatMap(book -> Mono.just(book.getAuthors()))
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @GetMapping("/genres/{bookId}")
    public Mono<ResponseEntity<List<Genre>>> getAllGenresByBookId(@PathVariable("bookId") String bookId) {
        return repository.findById(bookId)
                .flatMap(book -> Mono.just(book.getGenres()))
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }
}
