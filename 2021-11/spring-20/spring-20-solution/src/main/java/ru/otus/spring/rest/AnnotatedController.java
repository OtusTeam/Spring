package ru.otus.spring.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.rest.dto.PersonConverter;

import java.time.Duration;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@RestController
public class AnnotatedController {

    @GetMapping("/flux/one")
    public Mono<String> one() {
        return Mono.just("one")
                .map(this::transform);
    }

    @GetMapping("/flux/ten")
    public Flux<Integer> list() {
        return Flux.range(1, 10);
    }

    @GetMapping(path = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> stream() {
        return Flux.generate(() -> 0, (state, emitter) -> {
            emitter.next(state);
            return state + 1;
        })
                .delayElements(Duration.ofSeconds(1L))
                .map(i -> "" + i);
    }

    private String transform(String input) {
        String newResult = input.toUpperCase(Locale.ROOT);
        return newResult;
    }
}
