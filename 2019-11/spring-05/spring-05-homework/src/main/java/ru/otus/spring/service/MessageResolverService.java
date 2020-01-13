package ru.otus.spring.service;

import java.util.Locale;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageResolverService {
    private final MessageSource messageSource;
    private final Locale locale;

    public String getText(String messageName, String... args) {
        return messageSource.getMessage(
                messageName,
                args,
                locale
        );
    }
}
