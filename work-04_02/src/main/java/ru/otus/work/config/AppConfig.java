package ru.otus.work.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.work.controller.TestController;
import ru.otus.work.service.AnswerService;
import ru.otus.work.service.InputOutputService;
import ru.otus.work.service.LocalizedMessage;
import ru.otus.work.service.QuestionService;

@Configuration
@ConfigurationProperties("application")
public class AppConfig {

    private String locale;
    private String questionsFileName;

    @Bean
    public TestController testController(QuestionService questionService,
                                         AnswerService answerService,
                                         LocalizedMessage localizedMessage,
                                         InputOutputService inputOutputService) {
        return new TestController(
                questionService,
                answerService,
                localizedMessage,
                inputOutputService
        );
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getQuestionsFileName() {
        return questionsFileName;
    }

    public void setQuestionsFileName(String questionsFileName) {
        this.questionsFileName = questionsFileName;
    }
}
