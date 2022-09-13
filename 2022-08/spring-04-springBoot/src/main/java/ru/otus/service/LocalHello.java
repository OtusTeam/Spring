package ru.otus.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.configs.AppProps;

import javax.annotation.PostConstruct;

@Component
public class LocalHello {
    private static final Logger logger = LoggerFactory.getLogger(LocalHello.class);

    private final MessageSource messageSource;
    private final AppProps props;
    private final String message;

    public LocalHello(MessageSource messageSource, AppProps props, @Value("${application.message}") String message) {
        this.messageSource = messageSource;
        this.props = props;
        this.message = message;
    }

    //!!! Вообще, PostConstruct - это плохая практика !!!
    @PostConstruct
    public void printHello() {
        var messageLocalized = messageSource.getMessage("hello.user", new String[]{"Ivan"}, props.getLocale());
        logger.info("Localization:{}", messageLocalized);

        logger.info("message from props:{}", props.getMessage());
        logger.info("message:{}", message);
    }
}
