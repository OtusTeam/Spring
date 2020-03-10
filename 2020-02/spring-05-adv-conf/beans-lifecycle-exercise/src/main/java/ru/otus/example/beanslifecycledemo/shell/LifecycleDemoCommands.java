package ru.otus.example.beanslifecycledemo.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.example.beanslifecycledemo.domain.Phone;

@RequiredArgsConstructor
@ShellComponent
public class LifecycleDemoCommands {

    private final Phone phone;

    @ShellMethod(value = "Call favorite number", key = {"call-favorite-number", "cfn"})
    public void callFavoriteNumber() {
        phone.callFavoriteNumber();
    }
}
