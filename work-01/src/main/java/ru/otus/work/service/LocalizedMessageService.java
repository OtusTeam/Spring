package ru.otus.work.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class LocalizedMessageService implements LocalizedMessage {

    private final MessageSource messageSource;

    @Value("${appLocale}")
    private String appLocale;

    @Override
    public String getMessage(String key, Object[] params) {

        Locale locale = new Locale(appLocale);

        return messageSource.getMessage(key,
                params,
                locale
        );
    }

    public LocalizedMessageService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
