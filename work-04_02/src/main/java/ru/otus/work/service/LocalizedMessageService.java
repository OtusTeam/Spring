package ru.otus.work.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.work.config.AppConfig;

import java.util.Locale;

@Service
public class LocalizedMessageService implements LocalizedMessage {

    private final MessageSource messageSource;
    private final AppConfig appConfig;

    @Override
    public String getMessage(String key, Object[] params) {

        Locale locale = new Locale(appConfig.getLocale());

        return messageSource.getMessage(key,
                params,
                locale
        );
    }

    public LocalizedMessageService(MessageSource messageSource,
                                   AppConfig appConfig) {
        this.messageSource = messageSource;
        this.appConfig = appConfig;
    }
}
