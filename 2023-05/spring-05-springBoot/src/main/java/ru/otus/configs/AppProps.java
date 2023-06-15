package ru.otus.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties(prefix = "application")
public class AppProps {

    private final String message;
    private final Locale locale;

    @ConstructorBinding
    public AppProps(String message, Locale locale) {
        this.message = message;
        this.locale = locale;
    }

    public String getMessage() {
        return message;
    }


    public Locale getLocale() {
        return locale;
    }
}
