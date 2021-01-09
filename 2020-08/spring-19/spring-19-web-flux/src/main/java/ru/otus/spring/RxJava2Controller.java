package ru.otus.spring;

import io.reactivex.Flowable;
import io.reactivex.Single;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class RxJava2Controller {

    @GetMapping("/rx/one")
    public Single<String> single() {
        return Single.just("one");
    }

    @GetMapping("/rx/ten")
    public Flowable<Integer> list() {
        return Flowable.range(1, 10);
    }

    @GetMapping("/rx/five")
    public Flowable<Integer> list5() {
        return list().limit(5).delay(5, TimeUnit.SECONDS);
    }
}
