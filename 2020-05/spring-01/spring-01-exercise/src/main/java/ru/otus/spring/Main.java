package ru.otus.spring;

//import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.domain.Person;
import ru.otus.spring.service.PersonService;

public class Main {


     static public void main(String[] args) {
        // TODO: создайте здесь класс контекста
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        // TODO: Получите Person Service
        PersonService personService = context.getBean(PersonService.class);
        // Получите Person "Ivan"
        Person ivan = personService.getByName("Ivan");
        System.out.println("name: " + ivan.getName() + " age: " + ivan.getAge());
    }
}
