package ru.otus.services;

public interface MenuCommandsProcessor {
    void showAllNotes();

    void addNewNote();

    void updateNote();

    void deleteNote();

    void stopApplication();
}
