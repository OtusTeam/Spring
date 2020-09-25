package ru.otus.example.ormdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.example.ormdemo.models.OtusStudent;
import ru.otus.example.ormdemo.repositories.OtusStudentRepositoryJpa;

import javax.persistence.EntityManager;
import java.util.Optional;

@SpringBootApplication
public class OrmDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrmDemoApplication.class, args);
	}

}
