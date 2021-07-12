package ru.otus.spring.homework3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import ru.otus.spring.homework3.service.QuestionService;

import java.util.Locale;

@SpringBootApplication
public class Homework3Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Homework3Application.class, args);
		QuestionService service = ctx.getBean(QuestionService.class);
		MessageSource msg = ctx.getBean(MessageSource.class);
		msg.getMessage("quiz-file", new String[] {} , Locale.forLanguageTag("ru-RU"));
		service.printQuestions();
		service.returnResult();
	}

}
