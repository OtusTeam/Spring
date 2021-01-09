package ru.otus.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.otus.spring.config.DaoConfig;
import ru.otus.spring.config.ServicesConfig;
import ru.otus.spring.domain.Person;
import ru.otus.spring.service.PersonService;

@ComponentScan
//@Configuration
//@Import({DaoConfig.class, ServicesConfig.class}) //- does not work
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
//        context.register(DaoConfig.class);
//        context.register(ServicesConfig.class);
//        context.refresh();
        PersonService service = context.getBean(PersonService.class);

        Person ivan = service.getByName("Ivan");
        System.out.println("name: " + ivan.getName() + " age: " + ivan.getAge());
    }
}
