package ru.otus.example.testconfigurationdemo.demo2;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.example.testconfigurationdemo.family.FamilyMember;
import ru.otus.example.testconfigurationdemo.family.parents.Father;

@ComponentScan({"ru.otus.example.testconfigurationdemo.family.parents",
        "ru.otus.example.testconfigurationdemo.family.childrens"})
@SpringBootConfiguration
public class TestSpringBootConfiguration {
    @Bean
    FamilyMember father() {
        return new Father();
    }
}
