package ru.otus.spring.homework3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.homework3.dao.QuestionDao;
import ru.otus.spring.homework3.service.IOService;
import ru.otus.spring.homework3.service.MessageService;
import ru.otus.spring.homework3.service.QuestionService;
import ru.otus.spring.homework3.service.QuestionServiceImpl;

import java.util.Scanner;

@Configuration
public class ServiceConfig {

//    @Bean
//    public QuestionServiceImpl questionService(QuestionDao dao, MessageService ms, IOService ios) {
//        return new QuestionServiceImpl(dao,  ms, ios);
//    }
}
