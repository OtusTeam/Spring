package ru.otus.services;

import ru.otus.model.Note;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NotesServiceImpl implements NotesService {
    private final Map<String, Note> notes;

    public NotesServiceImpl() {
        notes = new HashMap<>();
    }

    @Override
    public List<Note> getAll() {
        return notes.values().stream().map(Note::copy).collect(Collectors.toList());
    }

    @Override
    public void save(Note note) {
        notes.put(note.getId(), note);
    }

    @Override
    public void remove(String id) {
        notes.remove(id);
    }
}
