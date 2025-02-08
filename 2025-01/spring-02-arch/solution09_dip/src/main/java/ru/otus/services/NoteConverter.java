package ru.otus.services;

import ru.otus.model.Note;

public interface NoteConverter {
    String convertNoteToString(int noteNumber, Note note);
}
