package ru.otus.work;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.work.controller.TestController;

import java.util.Locale;

@SpringBootApplication
@ComponentScan
public class Work04Application {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(Work04Application.class, args);

        // Дефолтная локаль
        Locale.setDefault(Locale.US);

        TestController testController = context.getBean(TestController.class);
        testController.runTest();
    }
}
