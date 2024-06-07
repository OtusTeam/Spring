package ru.otus.services.processors;

import ru.otus.model.Note;
import ru.otus.services.NotesService;
import ru.otus.services.menu.MenuOption;

import static ru.otus.services.processors.utils.NotesListUtil.checkNoteNumber;

public class UpdateNoteSingleCommandProcessor implements MenuSingleCommandProcessor {
    private final MenuOption processedCommandOption;

    private final InputService inputService;

    private final NotesService notesService;

    public UpdateNoteSingleCommandProcessor(InputService inputService, NotesService notesService,
                                            MenuOption processedCommandOption) {
        this.inputService = inputService;
        this.notesService = notesService;
        this.processedCommandOption = processedCommandOption;
    }

    @Override
    public void processCommand() {
        var notes = notesService.getAll();

        var updatedNoteNumber = inputService.readIntWithPrompt("Введите номер изменяемой заметки...");
        checkNoteNumber(updatedNoteNumber, notes.size());

        var noteText = inputService.readStringWithPrompt("Введите текст заметки...");

        var updatedNote = notes.get(updatedNoteNumber - 1);
        notesService.save(Note.of(updatedNote.getId(), noteText));
    }

    @Override
    public MenuOption getProcessedCommandOption() {
        return processedCommandOption;
    }


}
