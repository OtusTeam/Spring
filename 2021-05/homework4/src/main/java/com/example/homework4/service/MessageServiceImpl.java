package com.example.homework4.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageServiceImpl implements MessageService{
    private final Locale locale;
    private final ResourceBundleMessageSource msg;

    public MessageServiceImpl(@Value("${application.locale}") String tag, ResourceBundleMessageSource msg) {
        this.locale = Locale.forLanguageTag(tag);
        this.msg = msg;
    }

    public String getMessage(String text) {
        return msg.getMessage(text, new String[]{}, locale);
    }
}
