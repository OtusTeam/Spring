package ru.otus.example.applicationeventsdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.command.annotation.CommandScan;

@CommandScan
@SpringBootApplication
public class ApplicationEventsDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationEventsDemoApplication.class, args);
	}

}
