package ru.otus;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.services.GameProcessor;

@ComponentScan(basePackages = "ru.otus.services")
@Configuration
public class App {
    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(App.class);
        GameProcessor gameProcessor = ctx.getBean(GameProcessor.class);
        gameProcessor.startGame();
    }
}
