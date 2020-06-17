package ru.otus.spring.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Topic;

@Service
public class TopicPresentationServiceImpl implements TopicPresentationService {
    @Override
    public void showQuestion(Topic topic) {
        System.out.println(String.format("Topic: %s", topic.getQuestionText()));
        System.out.println("Choose answer's number");
        for (int i = 0; i < topic.getAnswers().size(); i++) {
            System.out.println(String.format("%d: %s", i + 1, topic.getAnswers().get(i)));
        }
    }

    @Override
    public boolean getUserAnswer(Topic topic) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your answer: ");
        if (scanner.hasNextInt() && scanner.nextInt() == topic.getCorrectAnswer()) {
            System.out.println("It's correct answer!");
            System.out.println();
            return true;
        } else {
            System.out.println("It's not correct answer!");
            System.out.println();
            return false;
        }
    }

    @Override
    public String getUserName() {
        System.out.print("Enter user name: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    @Override
    public String getUserSurname() {
        System.out.print("Enter user surname: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    @Override
    public void showResult(String name, String surname, int correct, int total) {
        System.out.println(String.format("Dear %s %s", name, surname));
        System.out.println(String.format("Your result %d correct answers from %d total", correct, total));
    }

    @Override
    public void showMark(boolean pass) {
        System.out.println(pass ? "You successfully passed the exam" : "You fail the exam");
    }
}
