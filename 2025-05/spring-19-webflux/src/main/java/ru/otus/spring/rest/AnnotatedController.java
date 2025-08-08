package ru.otus.spring.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import reactor.core.scheduler.Scheduler;


@RestController
public class AnnotatedController {
    private static final Logger logger = LoggerFactory.getLogger(AnnotatedController.class);

    private final Scheduler workerPool;

    public AnnotatedController(Scheduler workerPool) {
        this.workerPool = workerPool;
    }

    @GetMapping("/flux/one")
    public Mono<String> one() {
        return Mono.just("one");
    }

    @GetMapping(path ="/flux/ten", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Integer> list() {
        logger.info("list request");
        return Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1), workerPool)
                .doOnNext(val -> logger.info("value:{}", val));
    }

    @GetMapping(path = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> stream() {
        logger.info("stream");
        return Flux.generate(() -> 0, (state, emitter) -> {
                    emitter.next(state);
                    return state + 1;
                })
                .delayElements(Duration.ofSeconds(1L))
                .map(Object::toString)
                .map(val -> String.format("valStr:%s", val));
    }
}
