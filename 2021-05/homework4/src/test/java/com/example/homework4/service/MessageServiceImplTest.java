package com.example.homework4.service;//package ru.otus.spring.homework4.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Методы сервиса сообщений: ")
@SpringBootTest(classes = MessageServiceImpl.class)
@SpringBootConfiguration
class MessageServiceImplTest {
    @Configuration
    static class NestedConfiguration {

        @Bean
        ResourceBundleMessageSource msg() {
            ResourceBundleMessageSource msg = new ResourceBundleMessageSource();
            msg.setDefaultLocale(Locale.forLanguageTag("RU-ru"));
            return msg;
        }

    }

    @Autowired
    private MessageServiceImpl messageService;

    @MockBean
    private ResourceBundleMessageSource msg;

    @Test
    @DisplayName("Текущий метод: getMessage с русской локализацией")
    void shouldSayRussianGreeting() {
       // msg.setBasename("/messages");
    //    given(msg.getMessage("test.greeting", new String[]{}, Locale.forLanguageTag("RU-ru"))).willReturn("Привет");
        assertThat(messageService.getMessage("test.greeting").toString().equals("Привет"));
        verify(msg, times(1)).getMessage("test.greeting", new String[]{}, Locale.forLanguageTag("RU-ru")); //проверить, что вызвался msg
    }
}