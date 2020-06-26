package ru.otus.spring.service;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.configs.ApplicationProps;
import ru.otus.spring.domain.Topic;

@Service
public class TopicPresentationServiceImpl implements TopicPresentationService {
    private final MessageResolverService ms;

    @Autowired
    public TopicPresentationServiceImpl(MessageResolverService ms) {
        this.ms = ms;
    }

    @Override
    public void showQuestion(Topic topic) {
        System.out.println(ms.getText("topic.title", topic.getQuestionText()));
        System.out.println(ms.getText("topic.preanswers"));
        for (int i = 0; i < topic.getAnswers().size(); i++) {
            System.out.println(String.format("%d: %s", i + 1, topic.getAnswers().get(i)));
        }
    }

    @Override
    public boolean getUserAnswer(Topic topic) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(ms.getText("topic.answerquery"));
        if (scanner.hasNextInt() && scanner.nextInt() == topic.getCorrectAnswer()) {
            System.out.println(ms.getText("answer.correct"));
            System.out.println();
            return true;
        } else {
            System.out.println(ms.getText("answer.incorrect"));
            System.out.println();
            return false;
        }
    }

    @Override
    public String getUserName() {
        System.out.print(ms.getText("user.queryname"));
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    @Override
    public String getUserSurname() {
        System.out.print(ms.getText("user.querysurname"));
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    @Override
    public void showResult(String name, String surname, int correct, int total) {
        System.out.println(ms.getText("user.greeting", name, surname));
        System.out.println(ms.getText("exam.result",
                Integer.toString(correct), Integer.toString(total)));
    }

    @Override
    public void showMark(boolean pass) {
        System.out.println(
                pass
                        ? ms.getText("exam.success")
                        : ms.getText("exam.fail")
        );
    }
}
