package ru.otus.example.ormdemo;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class OrmDemoApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(OrmDemoApplication.class, args);
		Console.main(args);
	}

}
