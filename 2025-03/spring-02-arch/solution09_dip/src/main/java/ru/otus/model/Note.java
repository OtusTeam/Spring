package ru.otus.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Note {
    private final String id;
    private final LocalDateTime creationTime;
    private final String text;

    public Note(String text) {
        this.id = UUID.randomUUID().toString();
        this.creationTime = LocalDateTime.now();
        this.text = text;
    }

    public Note(String id, String text) {
        this.id = id;
        this.creationTime = LocalDateTime.now();
        this.text = text;
    }

    public Note(String id, LocalDateTime creationTime, String text) {
        this.id = id;
        this.creationTime = creationTime;
        this.text = text;
    }

    public static Note of(String text) {
        return new Note(text);
    }

    public static Note of(String id, String text) {
        return new Note(id, text);
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public String getText() {
        return text;
    }


    public Note copy() {
        return new Note(id, creationTime, text);
    }
}
