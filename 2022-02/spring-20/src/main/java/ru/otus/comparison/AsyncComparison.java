package ru.otus.comparison;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.io.IOException;

public class AsyncComparison {
    
    public static void main(String[] args) throws IOException {
        final long timeStarted = System.currentTimeMillis();
        final Observable<String> obs = controller();
        obs.subscribe(System.out::println);
        System.out.println("Wait time " + (System.currentTimeMillis() - timeStarted));
        System.in.read();
    }
    
    static Observable<String> controller() {
        return service();
    }
    
    static Observable<String> service() {
        return repository();
    }
    
    static Observable<String> repository() {
        return database();
    }
    
    static Observable<String> database() {
        return Observable.defer(() -> {
            try {
                Thread.sleep(4000);
            } catch (Exception e) {
                System.out.println("Don't do this");
            }
            return Observable.just("Hello world");
        }).subscribeOn(Schedulers.newThread());
    }
}
