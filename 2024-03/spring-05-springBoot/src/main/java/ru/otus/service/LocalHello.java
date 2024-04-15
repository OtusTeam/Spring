package ru.otus.service;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.otus.configs.AppProps;
import ru.otus.configs.ApplicationMessage;


@Component
public class LocalHello {
    private static final Logger logger = LoggerFactory.getLogger(LocalHello.class);

    private final AppProps props;
    private final String message;

    public LocalHello(AppProps props, ApplicationMessage applicationMessage) {
        this.props = props;
        this.message = applicationMessage.message();
    }

    //!!! Вообще, PostConstruct - это плохая практика !!!
    @PostConstruct
    public void printHello() {
        logger.info("message from props:{}", props.getMessage());
        logger.info("message:{}", message);
    }
}
