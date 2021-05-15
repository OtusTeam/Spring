package ru.otus.spring;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Person;
import ru.otus.spring.repository.PersonRepository;

import java.util.Arrays;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.queryParam;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.*;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class);
        PersonRepository repository = context.getBean(PersonRepository.class);

        repository.saveAll(Arrays.asList(
                new Person("Pushkin", 22),
                new Person("Lermontov", 22),
                new Person("Tolstoy", 60)
        )).subscribe(p -> System.out.println(p.getLastName()));

    }

    @Bean
    public RouterFunction<ServerResponse> composedRoutes(PersonRepository repository) {
        return route()
                // Обратите внимание на использование хэндлера
                .GET("/func/person", accept(APPLICATION_JSON), new PersonHandler(repository)::list)
                // Обратите внимание на использование pathVariable
                .GET("/func/person/{id}", accept(APPLICATION_JSON),
                        request -> repository.findById(request.pathVariable("id"))
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


