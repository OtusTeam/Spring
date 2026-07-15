package ru.otus.spring.docker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.otus.spring.docker.model.Person;
import ru.otus.spring.docker.repository.PersonRepository;

@SpringBootApplication
@EnableJpaRepositories
public class DockerComposeExampleApplication {

	public static void main(String[] args) {
		//Код для примера, делать так конечно нельзя :)
		ApplicationContext context = SpringApplication.run(DockerComposeExampleApplication.class, args);
		PersonRepository repository = context.getBean(PersonRepository.class);
		repository.save(new Person("Ivan", "Ivanov"));
		System.out.println(repository.findAll());
	}

}
