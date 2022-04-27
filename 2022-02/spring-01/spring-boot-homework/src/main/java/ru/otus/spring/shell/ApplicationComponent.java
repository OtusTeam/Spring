package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.service.TaskService;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationComponent {

    private final TaskService taskService;

    private String userName;

    @ShellMethod(value = "Login command", key = {"l", "login"})
    public String login(@ShellOption String userName) {
        this.userName = userName;
        return String.format("Добро пожаловать: %s", userName);
    }

    @ShellMethod(value = "Start test command", key = {"s", "start"})
    @ShellMethodAvailability(value = "isUserNameExist")
    public void start() {
        taskService.passQuestions();
    }

    @ShellMethod(value = "Get Last Test Scores", key = {"r", "result"})
    @ShellMethodAvailability(value = "isUserNameExist")
    public void getLastTestScores() {
        taskService.getLastTestScores();
    }

    private Availability isUserNameExist() {
        return userName == null? Availability.unavailable("Сначала залогиньтесь"): Availability.available();
    }
}
