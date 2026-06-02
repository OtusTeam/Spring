package ru.otus.example.conditionalandprofilesdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.example.conditionalandprofilesdemo.model.Party;

@SpringBootApplication
public class ConditionalAndProfilesDemoApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(ConditionalAndProfilesDemoApplication.class, args);
		Party party = ctx.getBean(Party.class);
		party.printPartyMembers();
	}

}
