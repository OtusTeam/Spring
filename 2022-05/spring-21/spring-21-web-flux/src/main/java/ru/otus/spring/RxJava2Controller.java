package ru.otus.spring;

import io.reactivex.Flowable;
import io.reactivex.Single;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class RxJava2Controller {

    @GetMapping("/rx/one")
    public Single<Integer> single() {
        return Single.just("one")
                .map(s -> s.length());
    }

    // single()
    //   .subscribe(response -> request.sendToClient(response))

    @GetMapping(value = "/rx/ten")
    public Flowable<Long> list() {
        // --0--1--2--3--4--...
        return Flowable.interval(2, TimeUnit.SECONDS)
                .map(i -> i + 1);
    }
}
