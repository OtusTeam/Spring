package ru.otus.spring.homework4.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.homework4.service.QuestionService;

@ShellComponent
public class QuizCommands {
    private final QuestionService service;
    private String userName;

    @Autowired
    public QuizCommands(QuestionService service) {
        this.service = service;
    }

    @ShellMethod(value = "Login command", key = {"l", "login"})
    public String login(@ShellOption String userName) {
        this.userName = userName;
        return String.format("Hi, %s!", userName);
    }

    @ShellMethod(value = "Run quiz", key = {"r", "run"})
    @ShellMethodAvailability(value = "isRunQuizAvailable")
    public String runQuiz() {
        service.run();
        return "Quiz is finished!";
    }

    private Availability isRunQuizAvailable() {
        return userName == null? Availability.unavailable("Please login"): Availability.available();

    }
}
