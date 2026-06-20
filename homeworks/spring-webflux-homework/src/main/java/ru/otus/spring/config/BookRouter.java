package ru.otus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.otus.spring.config.component.BookHandler;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class BookRouter {

    @Bean
    public RouterFunction<ServerResponse> route(BookHandler bookHandler) {
        return RouterFunctions.route(GET("/func/api/books").and(accept(APPLICATION_JSON)), bookHandler::getAllBooks)
                .andRoute(GET("/func/api/books/{id}").and(accept(APPLICATION_JSON)), bookHandler::getBookById)
                .andRoute(POST("/func/api/books").and(accept(APPLICATION_JSON)).and(contentType(APPLICATION_JSON)), bookHandler::saveBook)
                .andRoute(PUT("/func/api/books/{id}").and(accept(APPLICATION_JSON)).and(contentType(APPLICATION_JSON)), bookHandler::changeBookName)
                .andRoute(DELETE("/func/api/books/{id}"), bookHandler::deleteBookById)
                .andRoute(GET("/func/api/books/comments/{id}").and(accept(APPLICATION_JSON)), bookHandler::getAllCommentsByBookId)
                .andRoute(GET("/func/api/books/authors/{id}").and(accept(APPLICATION_JSON)), bookHandler::getAllAuthorsByBookId)
                .andRoute(GET("/func/api/books/genres/{id}").and(accept(APPLICATION_JSON)), bookHandler::getAllGenresByBookId);
    }
}
