package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.i18n.LocaleContextHolder;
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
@PropertySource(value = {
        "classpath:application.yml",
        "classpath:i18n/messages.properties",
        "classpath:i18n/messages_ru_RU.properties"})
public class TaskServiceImpl implements TaskService {

    @Value("${application.passing.score}")
    private Integer passingScore;

    private static final String MESSAGES_QUESTION_TEXT = "messages.question.text";
    private static final String MESSAGES_ANSWER_OPTIONS_TEXT = "messages.answer.options.text";
    private static final String MESSAGES_ANSWER_TEXT = "messages.answer.text";
    private static final String MESSAGES_CORRECT_ANSWER_TEXT = "messages.correct.answer.text";
    private static final String MESSAGES_INCORRECT_ANSWER_TEXT = "messages.incorrect.answer.text";
    private static final String MESSAGES_CONGRATULATIONS_TEXT = "messages.congratulations.text";
    private static final String MESSAGES_PITY_TEXT = "messages.pity.text";
    private static final String MESSAGES_TEST_SCORES_TEXT = "messages.test.scores.text";
    private static final String MESSAGES_ERROR_PASS_TEST_TEXT = "messages.error.pass.test.text";

    private final CsvFileReaderDao dao;
    private final MessageSource messageSource;

    private AtomicReference<Integer> passCount;

    @Override
    public void passQuestions() {
        passCount = new AtomicReference<>(0);
        AtomicReference<Integer> questionCount = new AtomicReference<>(0);
        Scanner in = new Scanner(System.in);
        getTaskList().forEach(task -> {
            questionCount.set(questionCount.get() + 1);
            StringBuilder builder = new StringBuilder(questionCount + " " + getMessage(MESSAGES_QUESTION_TEXT) + " " + task.getQuestion() + "\n");
            builder.append(getMessage(MESSAGES_ANSWER_OPTIONS_TEXT)).append("\n");
            task.getAnswerOptionList().forEach(response -> builder.append("* ").append(response).append("\n"));
            builder.append(getMessage(MESSAGES_ANSWER_TEXT)).append(" ");
            out.print(builder);
            String answer = in.nextLine();
            if (Objects.equals(answer, task.getAnswer())) {
                out.println(getMessage(MESSAGES_CORRECT_ANSWER_TEXT));
                passCount.set(passCount.get() + 1);
            } else {
                out.println(getMessage(MESSAGES_INCORRECT_ANSWER_TEXT) + " " + task.getAnswer());
            }
        });

        if (checkPass(passCount.get())) {
            out.println(getMessage(MESSAGES_CONGRATULATIONS_TEXT));
        } else {
            out.println(getMessage(MESSAGES_PITY_TEXT));
        }
    }

    @Override
    public void getLastTestScores() {
        if (passCount != null) {
            out.println(String.format(getMessage(MESSAGES_TEST_SCORES_TEXT), passCount.get()));
        } else {
            out.println(getMessage(MESSAGES_ERROR_PASS_TEST_TEXT));
        }
    }

    private List<Task> getTaskList() {
        return dao.getTaskList();
    }

    private boolean checkPass(Integer passCount) {
        return passingScore.compareTo(passCount) <= 0;
    }

    private String getMessage(String key) {
        return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
    }
}
