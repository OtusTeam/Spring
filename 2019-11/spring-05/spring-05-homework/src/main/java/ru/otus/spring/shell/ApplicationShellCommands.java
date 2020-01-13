package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.service.ExamService;
import ru.otus.spring.service.MessageResolverService;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationShellCommands {
    private String userName;
    private Boolean isExamPassed;

    private final ExamService examService;
    private final MessageResolverService mrs;

    @ShellMethod(value = "Login user", key = {"login", "l"})
    public String login(@ShellOption(defaultValue = "noname") String userName) {
        this.userName = userName;
        return mrs.getText("user.successlogin", userName);
    }

    @ShellMethod(value = "Start the exam", key = {"exam", "ex"})
    @ShellMethodAvailability(value = "isStartExamCommandAvailable")
    public String startExam() {
        isExamPassed = examService.proceedExam(userName);
        return mrs.getText("exam.finish");
    }

    @ShellMethod(value = "Print the last result", key = {"result", "r"})
    @ShellMethodAvailability(value = "isPrintResultCommandAvailable")
    public String printResult() {
        return isExamPassed ? mrs.getText("exam.success") : mrs.getText("exam.fail");
    }

    private Availability isStartExamCommandAvailable() {
        return userName == null ? Availability.unavailable(mrs.getText("user.requirelogin")) : Availability.available();
    }

    private Availability isPrintResultCommandAvailable() {
        return isExamPassed == null ? Availability.unavailable(mrs.getText("exam.notfinished")) : Availability.available();
    }

}
