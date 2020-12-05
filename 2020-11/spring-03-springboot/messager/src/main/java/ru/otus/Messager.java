package ru.otus;

public class Messager {
    private final MessagerConfig config;

    public Messager(MessagerConfig config) {
        this.config = config;
    }

    public String sayMessage() {
        return config.getMessage();
    }
}
