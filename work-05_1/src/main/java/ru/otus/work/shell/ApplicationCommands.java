package ru.otus.work.shell;

import lombok.SneakyThrows;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Component;
import ru.otus.work.service.InputOutputService;
import ru.otus.work.service.LocalizedMessage;
import ru.otus.work.service.TestService;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@ShellComponent
@Component
public class ApplicationCommands {

    private String studentName;

    private final TestService testService;

    private final LocalizedMessage localizedMessage;

    private final InputOutputService inputOutputService;

    @ShellMethod(value = "Write yor Name", key = {"n", "name"})
    public void writeName(@ShellOption(defaultValue = "StudentName") String studentName) {
        this.studentName = studentName;
        inputOutputService.printMessage(
                localizedMessage.getMessage(
                        "toStartTesting",
                        null
                )
        );
    }

    @ShellMethod(value = "Start testing", key = {"s", "start"})
    @ShellMethodAvailability(value = "isNameSet")
    public void startTesting() {
        testService.runTest(studentName);
    }

    private Availability isNameSet() {
        return studentName == null ? Availability.unavailable(
                localizedMessage.getMessage(
                        "needWriteName",
                        null
                )) : Availability.available();
    }

    @ShellMethod(value = "Enter the answer to the question", key = {"a", "answer"})
    public void enterAnswer(@ShellOption(defaultValue = "-") String answer) {
        testService.putAnswer(testService.currentQuestion(), answer);
    }

    @ShellMethod(value = "Print results", key = {"r", "result"})
    public void printResults() {
        testService.printResults();
    }

    @PostConstruct
    public void postConstruct() {
        inputOutputService.printMessage(
                localizedMessage.getMessage("helloMessage",
                        null
                )
        );

        inputOutputService.printMessage(
                localizedMessage.getMessage(
                        "toEnterName",
                        null
                )
        );
    }

    public ApplicationCommands(TestService testService,
                               LocalizedMessage localizedMessage,
                               InputOutputService inputOutputService) {
        this.testService = testService;
        this.localizedMessage = localizedMessage;
        this.inputOutputService = inputOutputService;
    }
}
