package ru.otus.services.processors;

import ru.otus.services.NotesService;
import ru.otus.services.menu.MenuOption;


import static ru.otus.services.processors.utils.NotesListUtil.checkNoteNumber;

public class DeleteNoteSingleCommandProcessor implements MenuSingleCommandProcessor {
    private final MenuOption processedCommandOption;

    private final InputService inputService;

    private final NotesService notesService;

    public DeleteNoteSingleCommandProcessor(InputService inputService, NotesService notesService,
                                            MenuOption processedCommandOption) {
        this.inputService = inputService;
        this.notesService = notesService;
        this.processedCommandOption = processedCommandOption;
    }

    @Override
    public void processCommand() {
        var notes = notesService.getAll();

        var deletedNoteNumber = inputService.readIntWithPrompt("Введите номер удаляемой заметки...");
        checkNoteNumber(deletedNoteNumber, notes.size());

        var updatedNote = notes.get(deletedNoteNumber - 1);
        notesService.remove(updatedNote.getId());
    }

    @Override
    public MenuOption getProcessedCommandOption() {
        return processedCommandOption;
    }

}
