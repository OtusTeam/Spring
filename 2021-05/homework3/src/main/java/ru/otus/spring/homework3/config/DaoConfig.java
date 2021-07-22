package ru.otus.spring.homework3.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.homework3.dao.QuestionDao;
import ru.otus.spring.homework3.dao.QuestionDaoSimple;
import ru.otus.spring.homework3.service.FileIOService;
import ru.otus.spring.homework3.service.IOService;
import ru.otus.spring.homework3.service.MessageService;
import ru.otus.spring.homework3.util.Parser;
import ru.otus.spring.homework3.util.ParserImpl;

@Configuration
public class DaoConfig {

    @Bean
    public Parser parser(FileIOService ios, MessageService ms) {
        return new ParserImpl(ios);
    }

    @Bean
    public QuestionDao questionDao(Parser parser) {
        return new QuestionDaoSimple(parser);
    }
}
