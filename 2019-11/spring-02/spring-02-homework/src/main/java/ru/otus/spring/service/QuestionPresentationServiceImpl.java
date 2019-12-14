package ru.otus.spring.service;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Question;

@Service
public class QuestionPresentationServiceImpl implements QuestionPresentationService {
    private final MessageResolverService mrs;

    @Autowired
    public QuestionPresentationServiceImpl(MessageResolverService mrs) {
        this.mrs = mrs;
    }

    @Override
    public void showQuestion(Question question) {
        System.out.println(mrs.getText("question.title", question.getQuestionText()));
        System.out.println(mrs.getText("question.preanswers"));
        for (int i = 0; i < question.getAnswers().size(); i++) {
            System.out.println(String.format("%d: %s", i + 1, question.getAnswers().get(i)));
        }
    }

    @Override
    public boolean getUserAnswer(Question question) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(mrs.getText("question.answerquery"));
        if (scanner.hasNextInt() && scanner.nextInt() == question.getCorrectAnswer()) {
            System.out.println(mrs.getText("answer.correct"));
            System.out.println();
            return true;
        } else {
            System.out.println(mrs.getText("answer.incorrect"));
            System.out.println();
            return false;
        }
    }

    @Override
    public String getUserName() {
        System.out.print(mrs.getText("user.queryname"));
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    @Override
    public String getUserSurname() {
        System.out.print(mrs.getText("user.querysurname"));
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    @Override
    public void showResult(String name, String surname, int correct, int total) {
        System.out.println(mrs.getText("user.greeting", name, surname));
        System.out.println(mrs.getText("exam.result", Integer.toString(correct), Integer.toString(total)));
    }

    @Override
    public void showMark(boolean pass) {
        System.out.println(mrs.getText(pass ? "exam.success" : "exam.fail"));
    }
}
