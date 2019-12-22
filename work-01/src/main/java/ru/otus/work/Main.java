package ru.otus.work;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.work.controller.TestController;

import java.util.Locale;

@ComponentScan
public class Main {

    public static void main(String[] args) {

        // Дефолтная локаль
        Locale.setDefault(Locale.US);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        TestController testController = context.getBean(TestController.class);

        testController.runTest();
    }
}
