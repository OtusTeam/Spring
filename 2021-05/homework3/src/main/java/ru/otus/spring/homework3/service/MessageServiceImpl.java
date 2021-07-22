package ru.otus.spring.homework3.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageServiceImpl implements MessageService{
    private final Locale locale;
    private final MessageSource msg;

    public MessageServiceImpl(@Value("${application.locale}") String tag, MessageSource msg) {
        this.locale = Locale.forLanguageTag(tag);
        this.msg = msg;
    }

    public String getMessage(String text) {
        return msg.getMessage(text, new String[]{}, locale);
    }
}
