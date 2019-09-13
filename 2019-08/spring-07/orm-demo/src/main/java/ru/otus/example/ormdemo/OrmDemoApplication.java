package ru.otus.example.ormdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ru.otus.example.ormdemo.config.EclipseLinkJpaConfiguration;

@SpringBootApplication
//@Import(EclipseLinkJpaConfiguration.class)
public class OrmDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrmDemoApplication.class, args);
	}

}
