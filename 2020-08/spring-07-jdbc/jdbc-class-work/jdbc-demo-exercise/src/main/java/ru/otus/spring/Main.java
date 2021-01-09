package ru.otus.spring;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.dao.PersonDao;
import ru.otus.spring.domain.Person;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {
        Integer a=5;
        long b = a;
        short c = (short) (int)a;

        ApplicationContext context = SpringApplication.run(Main.class);

        PersonDao dao = context.getBean(PersonDao.class);
        int count = dao.count();
        System.out.println("count = " + count);
        Person personById = dao.getPersonById(1L);
        System.out.println("Person" + personById);

//        Console.main(args);
    }
}
