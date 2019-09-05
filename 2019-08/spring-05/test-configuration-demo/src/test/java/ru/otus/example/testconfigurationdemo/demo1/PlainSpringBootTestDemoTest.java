package ru.otus.example.testconfigurationdemo.demo1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.example.testconfigurationdemo.family.FamilyMember;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("В PlainSpringBootTestDemoTest семья должна ")
@SpringBootTest
public class PlainSpringBootTestDemoTest {

    @Autowired
    private Map<String, FamilyMember> family;

    @DisplayName(" содержать маму, сына и собаку ")
    @Test
    void shouldContainAllFamilyExceptFather() {
        assertThat(family).containsOnlyKeys("mother", "son", "dog");
    }

}
