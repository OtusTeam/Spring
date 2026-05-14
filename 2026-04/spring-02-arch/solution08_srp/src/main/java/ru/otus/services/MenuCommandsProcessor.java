package ru.otus.services;

import ru.otus.model.Note;
import ru.otus.exceptions.NoteIndexOutOfBoundsException;

import java.util.stream.IntStream;

public class MenuCommandsProcessor {
    private final NotesService notesService;

    public MenuCommandsProcessor() {
        notesService = new NotesService();
    }

    public void showAllNotes(ConsoleIOService ioService) {
        var noteConverter = new NoteConverter();
        var notes = notesService.getAll();
        ioService.outputString("Заметки:");
        IntStream.range(1, notes.size() + 1)
                .mapToObj(k -> noteConverter.convertNoteToString(k, notes.get(k - 1)))
                .forEach(ioService::outputString);
        ioService.outputString("");
    }

    public void addNewNote(ConsoleIOService ioService) {
        var noteText = ioService.readStringWithPrompt("Введите текст заметки...");
        notesService.save(Note.of(noteText));
    }

    public void updateNote(ConsoleIOService ioService) {
        var notes = notesService.getAll();

        var updatedNoteNumber = ioService.readIntWithPrompt("Введите номер изменяемой заметки...");
        checkNoteNumber(updatedNoteNumber, notes.size());

        var noteText = ioService.readStringWithPrompt("Введите текст заметки...");

        var updatedNote = notes.get(updatedNoteNumber - 1);
        notesService.save(Note.of(updatedNote.getId(), noteText));
    }

    public void deleteNote(ConsoleIOService ioService) {
        var notes = notesService.getAll();

        var deletedNoteNumber = ioService.readIntWithPrompt("Введите номер удаляемой заметки...");
        checkNoteNumber(deletedNoteNumber, notes.size());

        var updatedNote = notes.get(deletedNoteNumber - 1);
        notesService.remove(updatedNote.getId());
    }

    private static void checkNoteNumber(int noteNumber, int notesCount) {
        if (noteNumber <= 0 || noteNumber > notesCount) {
            throw new NoteIndexOutOfBoundsException("Given number of note is out of range");
        }
    }
}
