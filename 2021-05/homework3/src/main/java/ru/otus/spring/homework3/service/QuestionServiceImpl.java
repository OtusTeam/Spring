package ru.otus.spring.homework3.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import ru.otus.spring.homework3.dao.QuestionDao;
import ru.otus.spring.homework3.domain.Question;

import java.util.Locale;
import java.util.Scanner;

@Getter
@Setter
@NoArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    @Value("${application.threshold}")
    private int threshold;
    private QuestionDao dao;
    private int totalPoints;
    private MessageSource msg;

    public QuestionServiceImpl(QuestionDao dao, int totalPoints, @Autowired MessageSource msg) {

        this.dao = dao;
        this.totalPoints = totalPoints;
        this.msg = msg;
    }

    @Override
    public void printQuestions() {
        Scanner scan = new Scanner(System.in);
        for (Question question : dao.getQuestions()) {
            question.print();
            int answerId = scan.nextInt();
            if (checkAnswer(question, answerId)) {
                System.out.println(msg.getMessage("feedback.ok", new String[] {}, Locale.forLanguageTag("ru-RU")));
            } else {
                System.out.println(msg.getMessage("feedback.no", new String[] {}, Locale.forLanguageTag("ru-RU")));
            }
        }
    }

    @Override
    public boolean isTestPassed() {
        return totalPoints > threshold;
    }

    @Override
    public boolean checkAnswer(Question question, int answerId) {
        boolean isRight = dao.isAnswerRight(question, answerId);
        if (isRight) {
            totalPoints++;
        }
        return isRight;
    }

    @Override
    public void returnResult() {
        if (isTestPassed()) {
            System.out.println(msg.getMessage("test.result.pass", new String[] {}, Locale.forLanguageTag("ru-RU")));
        } else {
            System.out.println(msg.getMessage("test.result.fail", new String[] {}, Locale.forLanguageTag("ru-RU")));
        }
    }
}
