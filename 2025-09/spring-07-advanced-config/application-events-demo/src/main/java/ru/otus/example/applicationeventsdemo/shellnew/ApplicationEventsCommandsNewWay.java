package ru.otus.example.applicationeventsdemo.shellnew;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.CommandAvailability;
import org.springframework.shell.command.annotation.Option;
import ru.otus.example.applicationeventsdemo.events.EventsPublisher;
import ru.otus.example.applicationeventsdemo.security.LoginContext;

@Command(group = "Application Events Commands New Way")
@RequiredArgsConstructor
public class ApplicationEventsCommandsNewWay {

    private final EventsPublisher eventsPublisher;

    private final LoginContext loginContext;

    @Command(description = "Login command new way", command = "login2", alias = "l2")
    public String login(@Option(defaultValue = "AnyUser") String userName) {
        loginContext.login(userName);
        return String.format("Добро пожаловать: %s", userName);
    }

    @Command(description = "Publish event command new way", command = "publish2", alias = {"p2", "pub2"})
    @CommandAvailability(provider = "publishEventCommandAvailabilityProvider")
    public String publishEvent() {
        eventsPublisher.publish();
        return "Событие опубликовано";
    }
}
