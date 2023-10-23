package ru.otus.example.testconfigurationdemo.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import ru.otus.example.testconfigurationdemo.family.FamilyMember;
import ru.otus.example.testconfigurationdemo.family.parents.Father;
import ru.otus.example.testconfigurationdemo.family.pets.Dog;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("В NestedTestConfigurationDemoTest семья должна ")
@SpringBootTest
//@TestPropertySource(properties = "spring.main.allow-bean-definition-overriding=true")
public class NestedTestConfigurationDemoTest {

    @TestConfiguration
    static class NestedTestConfiguration {
        @Bean
        FamilyMember father() {
            return new Father();
        }

/*
        @Bean
        FamilyMember dog() {
            return new Dog() {
                @Override
                public String getName() {
                    return "Злая собака";
                }
            };
        }
*/
    }

    @Autowired
    private Map<String, FamilyMember> family;

    @DisplayName(" содержать маму, папу, сына и собаку ")
    @Test
    void shouldContainAllFamilyWithFather() {
        assertThat(family).containsOnlyKeys("mother", "father", "son", "dog");
    }

}
