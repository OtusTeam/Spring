package ru.otus.work;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Locale;

@SpringBootApplication
@EnableWebMvc
public class WorkApplication {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        SpringApplication.run(WorkApplication.class);
    }
}
