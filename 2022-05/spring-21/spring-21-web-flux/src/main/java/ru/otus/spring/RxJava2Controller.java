package ru.otus.spring;

import io.reactivex.Flowable;
import io.reactivex.Single;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
