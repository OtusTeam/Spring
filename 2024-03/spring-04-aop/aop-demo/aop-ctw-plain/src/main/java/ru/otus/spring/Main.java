package ru.otus.spring;

import ru.otus.spring.dao.PersonDao;
import ru.otus.spring.dao.PersonDaoSimple;
import ru.otus.spring.domain.Person;
import ru.otus.spring.service.PersonService;
import ru.otus.spring.service.PersonServiceImpl;

/*
Запуск примера:
    1. cd aop-demo/aop-ctw-plain/
    2. mvn clean package
    3. java -jar target/aop-ctw-plain-1.0-jar-with-dependencies.jar
 */

public class Main {

	public static void main(String[] args) {
		PersonDao personDao = new PersonDaoSimple();
		PersonService service = new PersonServiceImpl(personDao);

		Person ivan = service.getByName("Ivan");
		System.out.println("name: " + ivan.getName() + " age: " + ivan.getAge());
	}
}
