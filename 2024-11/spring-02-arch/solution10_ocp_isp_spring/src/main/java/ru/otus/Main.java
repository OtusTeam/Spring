package ru.otus;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.services.ApplicationRunner;

// Spring
public class Main {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/spring-context.xml");
        ApplicationRunner runner = ctx.getBean(ApplicationRunner.class);
        runner.run();
    }
}
