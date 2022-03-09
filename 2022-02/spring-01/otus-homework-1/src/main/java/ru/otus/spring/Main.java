package ru.otus.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.domain.Task;
import ru.otus.spring.service.TaskService;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.System.out;

@ComponentScan(basePackages = "ru.otus.spring")
@PropertySource("classpath:application.properties")
public class Main {

    public static void main(String[] args) {
        //ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        TaskService taskService = context.getBean(TaskService.class);
        List<Task> taskList = taskService.getTaskList();
        AtomicReference<Integer> questionCount = new AtomicReference<>(0);
        AtomicReference<Integer> passCount = new AtomicReference<>(0);
        Scanner in = new Scanner(System.in);
        out.print("Your name: ");
        in.nextLine();
        taskList.forEach(task -> {
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

        if (taskService.checkPass(passCount.get())) {
            out.println("Congratulations! You have achieved a passing score");
        } else {
            out.println("Sorry! You didn't get a passing score");
        }
    }
}
