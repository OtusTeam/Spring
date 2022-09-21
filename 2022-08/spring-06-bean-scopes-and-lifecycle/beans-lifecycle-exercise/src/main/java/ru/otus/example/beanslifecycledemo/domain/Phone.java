package ru.otus.example.beanslifecycledemo.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "lifecycle.print.enabled", havingValue = "false")
@RequiredArgsConstructor
public class Phone {
    private String greeting = "Погнали к родителям";

    private final PhoneNumber favoriteNumber;

    public void callFavoriteNumber() {
        System.out.println(favoriteNumber.getOwnerName() + " " + greeting);
    }
}
