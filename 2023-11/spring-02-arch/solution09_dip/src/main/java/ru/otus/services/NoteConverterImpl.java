package ru.otus.services;

import ru.otus.config.AppSettings;
import ru.otus.model.Note;


import java.time.format.DateTimeFormatter;

public class NoteConverterImpl implements NoteConverter {
    private final AppSettings appSettings;

    public NoteConverterImpl(AppSettings appSettings) {
        this.appSettings = appSettings;
    }

    @Override
    public String convertNoteToString(int noteNumber, Note note) {
        var dateTimeFormatter = DateTimeFormatter.ofPattern(appSettings.getDateTimeFormat());
        return noteNumber + " | " + dateTimeFormatter.format(note.getCreationTime()) + " | " + note.getText();
    }
}
