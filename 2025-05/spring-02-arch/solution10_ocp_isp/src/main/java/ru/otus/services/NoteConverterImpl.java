package ru.otus.services;

import ru.otus.config.DateTimeFormatProvider;
import ru.otus.model.Note;

import java.time.format.DateTimeFormatter;

public class NoteConverterImpl implements NoteConverter {
    private final DateTimeFormatProvider dateTimeFormatProvider;

    public NoteConverterImpl(DateTimeFormatProvider dateTimeFormatProvider) {
        this.dateTimeFormatProvider = dateTimeFormatProvider;
    }

    @Override
    public String convertNoteToString(int noteNumber, Note note) {
        var dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeFormatProvider.getDateTimeFormat());
        return noteNumber + " | " + dateTimeFormatter.format(note.getCreationTime()) + " | " + note.getText();
    }
}
