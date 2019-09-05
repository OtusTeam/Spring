package ru.otus.testing.example.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.testing.example.TestingExampleSpringApplication;
import ru.otus.testing.example.dao.GreetingDao;

import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

// Тест с поднятием контекста spring
@DisplayName("Дао для работы с приветствиями ")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestingExampleSpringApplication.class)
class GreetingDaoImplTest {

    @Autowired
    private GreetingDao greetingDao;

    @ParameterizedTest
    @MethodSource("generateData")
    @DisplayName("дожен возвращать не пустое, корректное приветствие для кода указанной страны в любом регистре")
    void shouldReturnNotEmptyGreetingByCaseInsensitiveCountryCode(String countryCode, String expectedGreeting) {
        Optional<String> greetingOptional = greetingDao.findGreetingByCountryCode(countryCode);
        assertThat(greetingOptional).isNotEmpty().hasValue(expectedGreeting);
    }

    private static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of("ru", "Тестовый привет"),
                Arguments.of("RU", "Тестовый привет"),
                Arguments.of("en", "Тестовый hello"),
                Arguments.of("EN", "Тестовый hello"),
                Arguments.of("cn", "Тестовый nihao"),
                Arguments.of("CN", "Тестовый nihao")
        );
    }
}