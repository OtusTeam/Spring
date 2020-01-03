package ru.otus.work;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Locale;

@SpringBootApplication
@ComponentScan
public class Work04Application {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        ApplicationContext context = SpringApplication.run(Work04Application.class, args);
    }

}
