package ru.otus;

import io.reactivex.Observable;

import java.io.IOException;

public class LiveLikeExample {
    
    public static void main(String[] args) throws IOException {
        
        System.in.read();
    }
    
    static Observable<String> getName() {
        return Observable.just("Jake");
    }
    
    static Observable<String> getSurname() {
        return Observable.just("Foo");
    }
    
    static Observable<String> save(String fullName) {
        System.out.println(fullName + " saved!");
        return Observable.just("OK!");
    }
}
