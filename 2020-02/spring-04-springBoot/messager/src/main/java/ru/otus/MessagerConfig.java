package ru.otus;

public class MessagerConfig {
    private final String message;

    public MessagerConfig(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
