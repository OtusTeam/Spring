package ru.otus.services;

import ru.otus.model.Note;

import java.util.*;
import java.util.stream.Collectors;

public class NotesService {
    private final Map<String, Note> notes;

    public NotesService() {
        notes = new HashMap<>();
    }

    public List<Note> getAll() {
        return notes.values().stream().map(Note::copy).collect(Collectors.toList());
    }

    public void save(Note note) {
        notes.put(note.getId(), note);
    }

    public void remove(String id) {
        notes.remove(id);
    }
}
