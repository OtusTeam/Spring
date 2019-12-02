package ru.otus.spring.service;

import java.util.Scanner;

import ru.otus.spring.domain.Question;

public class QuestionPresentationServiceImpl implements QuestionPresentationService {
    @Override
    public void showQuestion(Question question) {
        System.out.println(String.format("Question: %s", question.getQuestionText()));
        System.out.println("Choose answer's number");
        for (int i = 0; i < question.getAnswers().size(); i++) {
            System.out.println(String.format("%d: %s", i + 1, question.getAnswers().get(i)));
        }
    }

    @Override
    public boolean getUserAnswer(Question question) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your answer: ");
        if (scanner.hasNextInt() && scanner.nextInt() == question.getCorrectAnswer()) {
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
}
