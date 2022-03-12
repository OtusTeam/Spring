package ru.otus.demo;

import ru.otus.demo.aop.LoggingAspect;
import ru.otus.demo.dao.BookDao;
import ru.otus.demo.dao.BookDaoSimple;
import ru.otus.demo.dao.PersonDao;
import ru.otus.demo.dao.PersonDaoSimple;
import ru.otus.demo.service.BookService;
import ru.otus.demo.service.BookServiceImpl;
import ru.otus.demo.service.PersonService;
import ru.otus.demo.service.PersonServiceImpl;
import ru.otus.demo.aop.SimpleWeaver;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        SimpleWeaver weaver = new SimpleWeaver();
        LoggingAspect aspect = new LoggingAspect();

        // Как будто контекст с бинами (пока без аспекта)
        Map<Class<?>, Object> context = new HashMap<>();
        context.put(PersonDao.class, new PersonDaoSimple());
        context.put(BookDao.class, new BookDaoSimple());

        // Колхозный вивинг
        context.forEach((key, value) -> context.put(key, weaver.weave(value, aspect)));

        // Как будто достаем из контекста дао (уже с примененным аспектом)
        PersonDao personDaoProxy = (PersonDao) context.get(PersonDao.class);
        BookDao bookDaoProxy = (BookDao) context.get(BookDao.class);

        // Внедряем в сервисы
        PersonService personService = new PersonServiceImpl(personDaoProxy);
        BookService bookService = new BookServiceImpl(bookDaoProxy);

        // Используем сервисы
        personService.getByName("Вася");
        System.out.println("-------------------------------------------------");
        bookService.getByTitle("Укротители велосипедов");
    }


}
