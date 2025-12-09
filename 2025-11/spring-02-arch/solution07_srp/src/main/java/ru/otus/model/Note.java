package ru.otus.model;

import java.time.LocalDateTime;

public class Note {
    private final LocalDateTime creationTime;
    private final String text;

    public Note(String text) {
        this.creationTime = LocalDateTime.now();
        this.text = text;
    }

    public static Note of(String text) {
        return new Note(text);
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public String getText() {
        return text;
    }
}
