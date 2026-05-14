package ru.otus.services;

import ru.otus.model.Note;

import java.util.List;

public interface NotesService {
    List<Note> getAll();

    void save(Note note);

    void remove(String id);
}
