package ru.otus.example.testconfigurationdemo.demo1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import ru.otus.example.testconfigurationdemo.family.FamilyMember;
import ru.otus.example.testconfigurationdemo.family.parents.Father;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("В NestedTestConfigurationDemoTest семья должна ")
@SpringBootTest
public class NestedTestConfigurationDemoTest {

    @TestConfiguration
    static class NestedTestConfiguration {
        @Bean
        FamilyMember father() {
            return new Father();
        }
    }

    @Autowired
    private Map<String, FamilyMember> family;

    @DisplayName(" содержать маму, папу, сына и собаку ")
    @Test
    void shouldContainAllFamilyWithFather() {
        assertThat(family).containsOnlyKeys("mother", "father", "son", "dog");
    }

}
