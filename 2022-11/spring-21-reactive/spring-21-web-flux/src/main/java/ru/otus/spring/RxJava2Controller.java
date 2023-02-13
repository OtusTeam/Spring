package ru.otus.spring;

import io.reactivex.Flowable;
import io.reactivex.Single;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class RxJava2Controller {
    private static final Logger logger = LoggerFactory.getLogger(RxJava2Controller.class);

    @GetMapping("/rx/one")
    public Single<Integer> single() {
        return Single.just("one")
                .map(String::length);
    }

    @GetMapping(value = "/rx/ten", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flowable<Long> list() {
        // --0--1--2--3--4--...
        return Flowable.interval(2, TimeUnit.SECONDS)
                .map(i -> i + 1)
                .doOnNext(item -> logger.info("item:{}", item));
    }

    @GetMapping(value = "/rx/five", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flowable<Long> five() {
        // --0--1--2--3--4--...
        return Flowable.interval(1, TimeUnit.SECONDS)
                .doOnNext(item -> logger.info("item:{}", item))
                .limit(5);
    }
}
