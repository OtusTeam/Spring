package ru.otus.example.beanslifecycledemo.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Phone {
    private String greeting = "Погнали к родителям";

    private final PhoneNumber favoriteNumber;

    public void callFavoriteNumber() {
        System.out.println(favoriteNumber.getOwnerName() + " " + greeting);
    }
}
