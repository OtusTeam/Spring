package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.domain.Task;
import ru.otus.spring.service.CsvFileReaderService;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Your name: ");
        in.nextLine();
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        CsvFileReaderService service = context.getBean(CsvFileReaderService.class);
        List<Task> taskList = service.getTaskList();
        final int[] questionCount = {0};
        taskList.forEach(task -> {
            StringBuilder builder = new StringBuilder(++questionCount[0] + " question: " + task.getQuestion() + "\n");
            builder.append("Answer options:\n");
            task.getAnswerOptionList().forEach(response -> builder.append("* " + response + "\n"));

            builder.append("Your answer: ");
            System.out.print(builder);
            String answer = in.nextLine();
            if (Objects.equals(answer, task.getAnswer())) {
                System.out.println(" YES! It is correct answer!");
            } else {
                System.out.println(" NO! Correct answer: " + task.getAnswer());
            }
        });
    }
}
