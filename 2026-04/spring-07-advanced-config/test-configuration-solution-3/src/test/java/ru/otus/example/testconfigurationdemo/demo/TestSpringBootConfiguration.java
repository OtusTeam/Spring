package ru.otus.example.testconfigurationdemo.demo;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import ru.otus.example.testconfigurationdemo.family.FamilyMember;
import ru.otus.example.testconfigurationdemo.family.parents.Father;
import ru.otus.example.testconfigurationdemo.family.pets.Dog;

@ComponentScan({"ru.otus.example.testconfigurationdemo.family.parents",
        "ru.otus.example.testconfigurationdemo.family.childrens"})

/*
@ComponentScan(value = "ru.otus.example.testconfigurationdemo.family",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = Dog.class))
*/
@SpringBootConfiguration
public class TestSpringBootConfiguration {
    @Bean
    FamilyMember father() {
        return new Father();
    }
}
