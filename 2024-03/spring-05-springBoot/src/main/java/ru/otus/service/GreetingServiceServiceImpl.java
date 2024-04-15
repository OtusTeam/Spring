package ru.otus.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import ru.otus.configs.AppProps;

@Service
public class GreetingServiceServiceImpl implements GreetingService {
    private static final Logger logger = LoggerFactory.getLogger(GreetingServiceServiceImpl.class);
    private final MessageSource messageSource;
    private final AppProps props;

    public GreetingServiceServiceImpl(MessageSource messageSource, AppProps props) {
        this.messageSource = messageSource;
        this.props = props;
        logger.info("props.data:{}", props.getData());
        logger.info("props.mapa:{}", props.getMapa());
    }

    @Override
    public Map<String, String> sayHello(String name) {
        var messageLocalized = messageSource.getMessage("hello.user", new String[]{"Ivan"}, props.getLocale());
        logger.info("Localization:{}", messageLocalized);

        Map<String, String> map = new HashMap<>();
        map.put(name, messageLocalized);
        return map;
    }
}
