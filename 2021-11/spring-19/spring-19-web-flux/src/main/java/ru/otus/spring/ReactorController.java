package ru.otus.spring;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.InputStream;
import java.time.Duration;

@RestController
public class ReactorController {

    /*
    {
        "age": 17,
        "name": "Ivan",
        "emails": ["ex1@mail.ru", "aaa@mail.ru"],
        "documnets": {
            "passprt": {"number": "5206"}
            "passprt2": {"number": "5206"}
        }
    }

    [
        {},
        {}
    ]

    11000

    true

    "string"

    null

     */

    @GetMapping("/flux/one")
    public Mono<String> one(@RequestBody InputStream json) {

        JsonNode

        return Mono.just("one");
    }

    @GetMapping("/flux/ten")
    public Flux<Integer> list() {
        return Flux.range(1, 10);
    }

    @GetMapping(path = "/flux/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> stream() {
        return Flux.generate(() -> 0, (state, emitter) -> {
            emitter.next(state);
            return state + 1;
        })
                .delayElements(Duration.ofSeconds(1L))
                .map(Object::toString);
    }
}
