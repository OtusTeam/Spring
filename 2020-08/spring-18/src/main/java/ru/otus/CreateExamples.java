package ru.otus;

import io.reactivex.Observable;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class CreateExamples {

    public static void main(String[] args) {
        Observable<String> obs = justExample();
        obs.forEach(System.out::println);
        obs.forEach(System.out::println);
    }
    
    public static final Observable<String> justExample() {
        return Observable.just("one", "two", "three");
    }
    
    public static Observable<String> createExample() {
        return Observable.create(emitter -> {
            if (emitter.isDisposed()) {
                return;
            }
            emitter.onNext("one");
            emitter.onNext("two");//!
            emitter.onNext("three");
            if (!emitter.isDisposed()) {
                emitter.onComplete();
            }
        });
    }
    
    public static Observable<String> deferExample() {
        return Observable.defer(() -> Observable.just("one", "two", "three"));
    }
}
