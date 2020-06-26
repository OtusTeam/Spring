package ru.otus.spring.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class MessageResolverService {
    private final MessageSource messageSource;
    private final Locale locale;

    @Autowired
    public MessageResolverService(MessageSource messageSource, Locale locale) {
        this.messageSource = messageSource;
        this.locale = locale;
    }

    public String getText(String messageName, String... args) {
        return messageSource.getMessage(messageName, args, locale);
    }
}
