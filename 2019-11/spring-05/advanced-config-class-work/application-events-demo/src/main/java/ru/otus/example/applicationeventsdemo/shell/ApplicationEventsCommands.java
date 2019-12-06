package ru.otus.example.applicationeventsdemo.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.example.applicationeventsdemo.events.EventsPublisher;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationEventsCommands {

    private final EventsPublisher eventsPublisher;

    private String userName;

    @ShellMethod(value = "Login command", key = {"l", "login"})
    public String login(@ShellOption(defaultValue = "stvort") String userName) {
        this.userName = userName;
        return String.format("Добро пожаловать: %s", userName);
    }

    @ShellMethod(value = "Publish event command", key = {"p", "pub", "publish"})
    @ShellMethodAvailability(value = "isPublishEventCommandAvailable")
    public String publishEvent() {
        eventsPublisher.publish();
        return "Событие опубликовано";
    }

    private Availability isPublishEventCommandAvailable() {
        return userName == null? Availability.unavailable("Сначала залогиньтесь"): Availability.available();
    }
}
