package ru.otus.work.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.otus.work.controller.TestController;
import ru.otus.work.service.AnswerService;
import ru.otus.work.service.LocalizedMessage;
import ru.otus.work.service.QuestionService;

@Configuration
public class AppConfig {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource resourceBundleMessageSource =
                new ReloadableResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename("/i18n/bundle");
        resourceBundleMessageSource.setDefaultEncoding("UTF-8");
        return resourceBundleMessageSource;
    }

    @Bean
    public TestController testController(QuestionService questionService,
                                         AnswerService answerService,
                                         LocalizedMessage localizedMessage) {
        return new TestController(
                questionService,
                answerService,
                localizedMessage
        );
    }
}
