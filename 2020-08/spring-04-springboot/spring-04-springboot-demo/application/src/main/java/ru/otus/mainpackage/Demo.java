package ru.otus.mainpackage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.otus.mainpackage.configs.AppProps;

//http://localhost:8080/actuator/health

@SpringBootApplication
@EnableConfigurationProperties(AppProps.class)
public class Demo {

    public static void main(String[] args) {
        SpringApplication.run(Demo.class, args);
    }
}
