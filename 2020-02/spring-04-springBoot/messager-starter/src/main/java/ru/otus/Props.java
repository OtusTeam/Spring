package ru.otus;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="messager")
public class Props {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
