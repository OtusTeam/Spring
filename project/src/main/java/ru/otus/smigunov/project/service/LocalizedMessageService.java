package ru.otus.smigunov.project.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class LocalizedMessageService implements LocalizedMessage {

    @Value("${application.locale}")
    private String locale;

    private final MessageSource messageSource;

    @Override
    public String getMessage(String key, Object[] params) {

        Locale localeObj = new Locale(locale);

        return messageSource.getMessage(
                key,
                params,
                localeObj
        );
    }

    public LocalizedMessageService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
