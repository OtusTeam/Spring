package ru.otus.spring.homework3.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.homework3.dao.QuestionDao;
import ru.otus.spring.homework3.service.QuestionService;
import ru.otus.spring.homework3.service.QuestionServiceImpl;

@Configuration
public class ServiceConfig {

    @Bean
    public QuestionService questionService(QuestionDao dao, MessageSource msg) {
        return new QuestionServiceImpl(dao, 0, msg);
    }
}
