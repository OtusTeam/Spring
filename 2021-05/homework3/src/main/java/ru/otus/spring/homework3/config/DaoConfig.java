package ru.otus.spring.homework3.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.homework3.dao.QuestionDao;
import ru.otus.spring.homework3.dao.QuestionDaoSimple;
import ru.otus.spring.homework3.util.Parser;
import ru.otus.spring.homework3.util.ParserImpl;

@Configuration
public class DaoConfig {

    @Bean
    public Parser parser(MessageSource msg) {
        return new ParserImpl(msg);
    }

    @Bean
    public QuestionDao questionDao(Parser parser) {
        return new QuestionDaoSimple(parser);
    }
}
