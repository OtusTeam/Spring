package ru.otus.work;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.work.domain.Answer;
import ru.otus.work.domain.Question;
import ru.otus.work.service.AnswerService;
import ru.otus.work.service.QuestionService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuestionService questionService = context.getBean(QuestionService.class);
        AnswerService answerService = context.getBean(AnswerService.class);

        System.out.println("Тест по истории. Введите ваши фамилия и имя.");

        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        for (Question question : questionService.listQuestions()) {
            System.out.println(question.getText());

            Answer answer = new Answer();
            answer.setQuestion(question.getText());

            scanner = new Scanner(System.in);
            String answerFromKeyboard = scanner.nextLine();
            answer.setAnswer(answerFromKeyboard);

            answerService.saveAnswer(answer);
        }

        System.out.println(String.format("Ответы на тест студента '%s'\n", name));

        for (Answer answer : answerService.listAnswers()) {
            System.out.println(String.format("Вопрос: %s ответ: %s", answer.getQuestion(), answer.getAnswer()));
        }

        System.out.println("\nПравильные ответы:\n");

        for (Question question : questionService.listQuestions()) {
            System.out.println(String.format("%s ответ: %s", question.getText(), question.getAnswer()));
        }
    }
}
