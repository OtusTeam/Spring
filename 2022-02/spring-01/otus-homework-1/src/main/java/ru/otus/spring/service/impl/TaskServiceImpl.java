package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.CsvFileReaderDao;
import ru.otus.spring.domain.Task;
import ru.otus.spring.service.TaskService;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.System.out;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:application.properties")
public class TaskServiceImpl implements TaskService {

    @Value("${passing.score}")
    private Integer passingScore;

    private final CsvFileReaderDao dao;

    @Override
    public void passQuestions() {
        AtomicReference<Integer> questionCount = new AtomicReference<>(0);
        AtomicReference<Integer> passCount = new AtomicReference<>(0);
        Scanner in = new Scanner(System.in);
        out.print("Your name: ");
        in.nextLine();
        getTaskList().forEach(task -> {
            questionCount.set(questionCount.get() + 1);
            StringBuilder builder = new StringBuilder(questionCount + " question: " + task.getQuestion() + "\n");
            builder.append("Answer options:\n");
            task.getAnswerOptionList().forEach(response -> builder.append("* " + response + "\n"));

            builder.append("Your answer: ");
            out.print(builder);
            String answer = in.nextLine();
            if (Objects.equals(answer, task.getAnswer())) {
                out.println(" YES! It is correct answer!");
                passCount.set(passCount.get() + 1);
            } else {
                out.println(" NO! Correct answer: " + task.getAnswer());
            }
        });

        if (checkPass(passCount.get())) {
            out.println("Congratulations! You have achieved a passing score");
        } else {
            out.println("Sorry! You didn't get a passing score");
        }
    }

    private List<Task> getTaskList() {
        return dao.getTaskList();
    }

    private boolean checkPass(Integer passCount) {
        return passingScore.compareTo(passCount) <= 0;
    }
}
