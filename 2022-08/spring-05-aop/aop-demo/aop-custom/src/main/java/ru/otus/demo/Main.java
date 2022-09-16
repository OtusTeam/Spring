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

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        // Как будто Bean Definitions
        List<Class<?>> beanDefinitions = List.of(PersonDaoSimple.class, BookDaoSimple.class);

        SimpleWeaver weaver = new SimpleWeaver();
        LoggingAspect aspect = new LoggingAspect();

        // Как будто контекст
        Map<Class<?>, Object> context = new HashMap<>();
        beanDefinitions.stream()
                // Создание бинов
                .map(Main::createInstance)
                // Постобработка, вивинг
                .forEach(bean -> context.put(bean.getClass().getInterfaces()[0],
                        weaver.weave(bean, aspect)));

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

    private static Object createInstance(Class<?> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
