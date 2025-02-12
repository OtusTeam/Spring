package ru.otus.spring.testing;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.testing.domain.SimpleQuestion;
import ru.otus.spring.testing.service.TestAppSimple;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static Pattern splitter;

    public static void main(String[] args){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("./spring-context.xml");
        TestAppSimple app = (TestAppSimple) context.getBean("testService");
        System.out.println("Let`s start our extremely difficult test!!!");
        System.out.println("===========================================");
        SimpleQuestion task = app.getNextQuestion();
        Scanner answerScanner = new Scanner(System.in);
        int solvedTasksAmount = 0;
        int tasksAmount = 0;

        while (task != null){
            tasksAmount++;
            System.out.println(System.lineSeparator() + "Question#"+tasksAmount+" :  " + task.getQuestion());
            System.out.print("Answer: ");
            task.giveAnswer(answerScanner.nextLine());
            System.out.println(task.check() ? "That's correct!" : "No, you are mistaken...");
            solvedTasksAmount += task.check() ? 1 : 0;

            task = app.getNextQuestion();
        }
        System.out.println(System.lineSeparator() + "Test is finished!");
        System.out.println("And your result is "+ solvedTasksAmount+ " of "+tasksAmount);
        System.out.println("===========================================" + System.lineSeparator());
    }
}
