package ru.otus.spring.config.component;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Book;
import ru.otus.spring.repositories.BookReactiveRepository;
import ru.otus.spring.rest.dto.request.ChangeBookInfoRequestDto;
import ru.otus.spring.rest.dto.request.CreateFullBookInfoRequestDto;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static org.springframework.web.reactive.function.server.ServerResponse.*;

@Component
public class BookHandler {

    private final BookReactiveRepository repository;


    public BookHandler(BookReactiveRepository repository) {
        this.repository = repository;
    }

    public Mono<ServerResponse> getAllBooks(ServerRequest request) {
        return ok().contentType(APPLICATION_JSON)
                .body(fromPublisher(repository.findAll(), Book.class));
    }

    public Mono<ServerResponse> getBookById(ServerRequest request) {
        final String id = request.pathVariable("id");
        final Mono<Book> book = repository.findById(id);
        return book
                .flatMap(p -> ok().contentType(APPLICATION_JSON).body(fromPublisher(book, Book.class)))
                .switchIfEmpty(notFound().build());
    }

    public Mono<ServerResponse> saveBook(ServerRequest request) {
        final Mono<CreateFullBookInfoRequestDto> dto = request.bodyToMono(CreateFullBookInfoRequestDto.class);
        return created(UriComponentsBuilder.fromPath("func/api/books/").build().toUri())
                .contentType(APPLICATION_JSON)
                .body(fromPublisher(
                        dto.map(Book::new).flatMap(repository::save), Book.class));
    }

    public Mono<ServerResponse> changeBookName(ServerRequest request) {
        final String id = request.pathVariable("id");
        final Mono<ChangeBookInfoRequestDto> dto = request.bodyToMono(ChangeBookInfoRequestDto.class);
        return repository.findById(id)
                .flatMap(old -> ok().contentType(APPLICATION_JSON)
                        .body(fromPublisher(dto
                                .flatMap(d -> {
                                    old.setName(d.getName());
                                    return repository.save(old);
                                }), Book.class)))
                .switchIfEmpty(notFound().build());
    }

    public Mono<ServerResponse> deleteBookById(ServerRequest request) {
        final String id = request.pathVariable("id");
        return repository
                .findById(id)
                .flatMap(b -> noContent().build(repository.delete(b)))
                .switchIfEmpty(notFound().build());
    }

    public Mono<ServerResponse> getAllCommentsByBookId(ServerRequest request) {
        final String id = request.pathVariable("id");
        final Mono<Book> book = repository.findById(id);
        return book
                .flatMap(p -> ok().contentType(APPLICATION_JSON).body(fromValue(p.getComments())))
                .switchIfEmpty(notFound().build());
    }

    public Mono<ServerResponse> getAllAuthorsByBookId(ServerRequest request) {
        final String id = request.pathVariable("id");
        final Mono<Book> book = repository.findById(id);
        return book
                .flatMap(p -> ok().contentType(APPLICATION_JSON).body(fromValue(p.getAuthors())))
                .switchIfEmpty(notFound().build());
    }

    public Mono<ServerResponse> getAllGenresByBookId(ServerRequest request) {
        final String id = request.pathVariable("id");
        final Mono<Book> book = repository.findById(id);
        return book
                .flatMap(p -> ok().contentType(APPLICATION_JSON).body(fromValue(p.getGenres())))
                .switchIfEmpty(notFound().build());
    }

}
