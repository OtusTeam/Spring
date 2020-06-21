package ru.otus.spring;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.context.annotation.*;
import ru.otus.spring.domain.Person;
import ru.otus.spring.service.PersonService;

//@Configuration
//@ComponentScan
//@EnableAspectJAutoProxy
public class Main {

    public static void main(String[] args) {
//        AnnotationConfigApplicationContext context =
//                new AnnotationConfigApplicationContext(Main.class);
//
//        PersonService service = context.getBean(PersonService.class);
//
//        Person ivan = service.getByName("Ivan");
//        System.out.println("name: " + ivan.getName() + " age: " + ivan.getAge());
        List<Object> collect = Optional.ofNullable(null).stream().collect(Collectors.toList());
        System.out.println(collect.toString());
    }
}
