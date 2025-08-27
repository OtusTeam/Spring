package ru.otus.example.conditionalandprofilesdemo.model;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.otus.example.conditionalandprofilesdemo.model.base.Friend;

@Profile("Oleg")
@Component
public class Oleg extends Friend {
    @Override
    public String getName() {
        return "Олег";
    }
}
