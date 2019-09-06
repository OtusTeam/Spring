package ru.otus.spring03;

import org.springframework.context.annotation.*;
import ru.otus.spring03.domain.Person;
import ru.otus.spring03.service.PersonService;

@EnableAspectJAutoProxy
@Configuration
@ComponentScan
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);

        PersonService service = context.getBean(PersonService.class);

        Person ivan = service.getByName("Ivan");
        System.out.println("name: " + ivan.getName() + " age: " + ivan.getAge());
    }
}
