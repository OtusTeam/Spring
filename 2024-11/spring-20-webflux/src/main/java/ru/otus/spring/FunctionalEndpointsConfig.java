package ru.otus.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Person;
import ru.otus.spring.repository.PersonRepository;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.queryParam;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.badRequest;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class FunctionalEndpointsConfig {
    @Bean
    public RouterFunction<ServerResponse> composedRoutes(PersonRepository repository) {
        return route()
                // эта функция должна стоять раньше findAll - порядок следования роутов - важен
                .GET("/func/person",
                        queryParam("name", param -> param != null && !param.isEmpty()),
                        request -> request.queryParam("name")
                                .map(name -> ok().body(repository.findAllByLastName(name), Person.class))
                                .orElse(badRequest().build())
                )
                // пример другой реализации - начиная с запроса репозитория
                .GET("/func/person", queryParam("age", param -> param != null && !param.isEmpty()),
                        request ->
                                ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .body(repository.findAllByAge(request.queryParam("age")
                                                        .map(Integer::parseInt)
                                                        .orElseThrow(IllegalArgumentException::new)), Person.class)
                )
                // Обратите внимание на использование хэндлера
                .GET("/func/person", accept(APPLICATION_JSON), new PersonHandler(repository)::list)
                // Обратите внимание на использование pathVariable
                .GET("/func/person/{id}", accept(APPLICATION_JSON),
                        request -> repository.findById(Long.parseLong(request.pathVariable("id")))
                                .flatMap(person -> ok().contentType(APPLICATION_JSON).body(fromValue(person)))
                                .switchIfEmpty(notFound().build())
                ).build();
    }

    // Это пример хэндлера, который даже не бин
    static class PersonHandler {

        private final PersonRepository repository;

        PersonHandler(PersonRepository repository) {
            this.repository = repository;
        }

        Mono<ServerResponse> list(ServerRequest request) {
            // Обратите внимание на пример другого порядка создания response от Flux
            return ok().contentType(APPLICATION_JSON).body(repository.findAll(), Person.class);
        }
    }
}
