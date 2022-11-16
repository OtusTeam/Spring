package ru.otus;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateExample {
    private static final Logger logger = LoggerFactory.getLogger(CreateExample.class);

    public static void main(String[] args) {
        //onEachNext();
        //lazyObservable();
        //creatorExample();
    }

    private static void creatorExample() {
        Observable<String> obs = createExample();

        obs.onExceptionResumeNext(Observable.just("r1", "r2", "r3"))
                .doOnNext(item -> logger.info("item__1:{}", item))
                .subscribe();

        logger.info("---------------");

        Disposable disposable = obs.doOnNext(item -> logger.info("item__2:{}", item))
                .subscribe(next -> logger.info("next:{}", next),
                         error -> logger.info("error:{}", error.getMessage()),
                        () -> logger.info("onComplete"));

        logger.info("isDisposed:{}", disposable.isDisposed());
    }

    private static void lazyObservable() {
        Observable<String> obs = deferExample();

        obs.doOnNext(item -> logger.info("item_1:{}", item))
                .subscribe();
        obs.doOnNext(item -> logger.info("item_2:{}", item))
                .subscribe();
    }

    private static void onEachNext() {
        Observable<String> obs = justExample();
        obs.doOnEach(item -> logger.info("item_1:{}", item.getValue()))
                .subscribe();
        obs.doOnNext(item -> logger.info("item_2:{}", item))
                .map(String::length)
                .doOnNext(item -> logger.info("length_2:{}", item))
                .subscribe();
    }

    private static Observable<String> justExample() {
        return Observable.just("one", "two", "three");
    }

    private static Observable<String> deferExample() {
        return Observable.defer(() -> {
            logger.info("creating new Observable");
            return Observable.just("one", "two", "three");
        });
    }

    private static Observable<String> createExample() {
        return Observable.create(emitter -> {
            emitter.onNext("one");
            emitter.onNext("two");

            emitter.onError(new RuntimeException("Error!"));

            emitter.onNext("three");
            emitter.onComplete();
        });
    }
}
