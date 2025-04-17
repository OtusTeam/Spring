package ru.otus.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ru.otus.spring.domain.Person;
import ru.otus.spring.service.PersonService;

/*
Запуск примера:
    1. В IDEA, вменю запуска выбираем "Edit Configurations..."
    2. В поле "VM options" вносим "-javaagent:${путь.до.m2}\.m2\repository\org\aspectj\aspectjweaver\1.9.8\aspectjweaver-1.9.8.jar",
       где ${путь.до.m2} это путь до репозитория мавен на текущем компьютере.
       Пример: "-javaagent:c:\Users\MyUserName\.m2\repository\org\aspectj\aspectjweaver\1.9.9.1\aspectjweaver-1.9.9.1.jar".
       Кавычки не вносим)
    3. Запускаем Main
*/

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
