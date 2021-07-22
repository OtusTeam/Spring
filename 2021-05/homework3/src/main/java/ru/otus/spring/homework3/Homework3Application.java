package ru.otus.spring.homework3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import ru.otus.spring.homework3.service.QuestionService;
import ru.otus.spring.homework3.service.QuestionServiceImpl;

import java.util.Locale;

@SpringBootApplication
public class Homework3Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Homework3Application.class, args);
		QuestionService service = ctx.getBean(QuestionServiceImpl.class);
		service.run();
	}
}
