package ru.otus.service;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.configs.AppProps;


@Component
public class LocalHello {
    private static final Logger logger = LoggerFactory.getLogger(LocalHello.class);

    private final AppProps props;
    private final String message;

    public LocalHello(AppProps props, @Value("${application.message}") String message) {
        this.props = props;
        this.message = message;
    }

    //!!! Вообще, PostConstruct - это плохая практика !!!
    @PostConstruct
    public void printHello() {
        logger.info("message from props:{}", props.getMessage());
        logger.info("message:{}", message);
    }
}
