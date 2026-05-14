package ru.otus.services;

import ru.otus.model.Note;

public class NoteConverter {
    public String convertNoteToString(int noteNumber, Note note) {
        return noteNumber + " | " + note.getCreationTime() + " | " + note.getText();
    }
}
