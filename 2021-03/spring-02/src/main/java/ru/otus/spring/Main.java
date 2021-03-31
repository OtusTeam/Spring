package ru.otus.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.dao.PersonDao;
import ru.otus.spring.dao.PersonDaoSimple;
import ru.otus.spring.domain.Person;
import ru.otus.spring.service.PersonService;
import ru.otus.spring.service.PersonServiceImpl;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext();

        PersonDao dao = new PersonDaoSimple();

        context.getBeanFactory().registerSingleton("personDao", dao);

        PersonServiceImpl personService = new PersonServiceImpl();
        personService.setDao(dao);

        context.getBeanFactory().registerSingleton("personService", personService);

        context.refresh();


        PersonService service = (PersonService) context.getBean("personService");
        Person ivan = service.getByName("Ivan");
        System.out.println("name: " + ivan.getName() + " age: " + ivan.getAge());


        // Данная операция, в принципе не нужна.
        // Мы не работаем пока что с БД, а Spring Boot сделает закрытие за нас
        // Подробности - через пару занятий
        context.close();
    }
}
