package ru.otus.testing.example.spring.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.testing.example.TestingExampleSpringApplication;
import ru.otus.testing.example.dao.GreetingDao;
import ru.otus.testing.example.services.GreetingNotFoundException;
import ru.otus.testing.example.services.GreetingService;
import ru.otus.testing.example.services.IOService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static ru.otus.testing.example.services.CountryCodes.*;

// Тест с поднятием контекста spring
@DisplayName("Методы сервиса приветствий должны ")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestingExampleSpringApplication.class, TestContextConfig.class})
class GreetingServiceImplTest {

    @Autowired
    private GreetingService greetingService;

    @Autowired
    private IOService ioService;

    @Autowired
    private GreetingDao greetingDao;


    @BeforeEach
    void setUp() {
        Mockito.reset(ioService, greetingDao);
        given(greetingDao.findGreetingByCountryCode(any())).willReturn(Optional.of(""));
    }

    @Test
    @DisplayName("вызывать методы ioService и greetingDao с нужными параметрами. Текущий метод: sayRussianGreeting")
    void shouldExecuteServiceMethodsForRussianGreeting() {
        greetingService.sayRussianGreeting();
        verify(ioService, times(1)).out("");
        verify(greetingDao, times(1)).findGreetingByCountryCode(COUNTRY_CODE_RU);
    }

    @Test
    @DisplayName("вызывать методы ioService и greetingDao с нужными параметрами. Текущий метод: sayEnglishGreeting")
    void shouldExecuteServiceMethodsForEnglishGreeting() {
        greetingService.sayEnglishGreeting();
        verify(ioService, times(1)).out("");
        verify(greetingDao, times(1)).findGreetingByCountryCode(COUNTRY_CODE_EN);
    }

    @Test
    @DisplayName("вызывать методы ioService и greetingDao с нужными параметрами. Текущий метод: sayChinaGreeting")
    void shouldExecuteServiceMethodsForChinaGreeting() {
        greetingService.sayChinaGreeting();
        verify(ioService, times(1)).out("");
        verify(greetingDao, times(1)).findGreetingByCountryCode(COUNTRY_CODE_CN);
    }

    @Test
    @DisplayName(" не бросать исключение если приветствие найдено")
    void shouldNotThrowExceptionIfGreetingExists() {
        assertThatCode(() -> greetingService.sayChinaGreeting()).doesNotThrowAnyException();

    }

    @Test
    @DisplayName(" бросать исключение если приветствие не найдено")
    void shouldThrowFoundExceptionIfGreetingNotExists() {
        given(greetingDao.findGreetingByCountryCode(any())).willReturn(Optional.empty());
        assertThatThrownBy(() -> greetingService.sayChinaGreeting()).isInstanceOf(GreetingNotFoundException.class);

    }

}