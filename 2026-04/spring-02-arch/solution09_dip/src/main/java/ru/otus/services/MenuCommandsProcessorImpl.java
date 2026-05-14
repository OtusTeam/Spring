package ru.otus.services;

import ru.otus.model.Note;
import ru.otus.exceptions.NoteIndexOutOfBoundsException;

import java.util.stream.IntStream;

public class MenuCommandsProcessorImpl implements MenuCommandsProcessor {
    private final IOService ioService;
    private final NotesService notesService;
    private final NoteConverter noteConverter;
    private final ApplicationStopService applicationStopService;

    public MenuCommandsProcessorImpl(IOService ioService, NotesService notesService,
                                     NoteConverter noteConverter,
                                     ApplicationStopService applicationStopService) {
        this.ioService = ioService;
        this.notesService = notesService;
        this.noteConverter = noteConverter;
        this.applicationStopService = applicationStopService;
    }

    @Override
    public void showAllNotes() {
        var notes = notesService.getAll();
        ioService.outputString("Заметки:");
        IntStream.range(1, notes.size() + 1)
                .mapToObj(k -> noteConverter.convertNoteToString(k, notes.get(k - 1)))
                .forEach(ioService::outputString);
        ioService.outputString("");
    }

    @Override
    public void addNewNote() {
        var noteText = ioService.readStringWithPrompt("Введите текст заметки...");
        notesService.save(Note.of(noteText));
    }

    @Override
    public void updateNote() {
        var notes = notesService.getAll();

        var updatedNoteNumber = ioService.readIntWithPrompt("Введите номер изменяемой заметки...");
        checkNoteNumber(updatedNoteNumber, notes.size());

        var noteText = ioService.readStringWithPrompt("Введите текст заметки...");

        var updatedNote = notes.get(updatedNoteNumber - 1);
        notesService.save(Note.of(updatedNote.getId(), noteText));
    }

    @Override
    public void deleteNote() {
        var notes = notesService.getAll();

        var deletedNoteNumber = ioService.readIntWithPrompt("Введите номер удаляемой заметки...");
        checkNoteNumber(deletedNoteNumber, notes.size());

        var updatedNote = notes.get(deletedNoteNumber - 1);
        notesService.remove(updatedNote.getId());
    }

    @Override
    public void stopApplication() {
        applicationStopService.stopApplication();
    }

    private static void checkNoteNumber(int noteNumber, int notesCount) {
        if (noteNumber <= 0 || noteNumber > notesCount) {
            throw new NoteIndexOutOfBoundsException("Given number of note is out of range");
        }
    }
    
}
