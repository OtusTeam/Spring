package ru.otus.spring;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import reactor.core.scheduler.Scheduler;
import ru.otus.spring.domain.Notes;
import ru.otus.spring.domain.Person;
import ru.otus.spring.repository.NotesRepository;
import ru.otus.spring.repository.PersonRepository;
import ru.otus.spring.repository.PersonRepositoryCustom;

@Component
public class DataFiller implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(DataFiller.class);

    private final PersonRepository personRepository;
    private final NotesRepository notesRepository;
    private final PersonRepositoryCustom personRepositoryCustom;
    private final Scheduler workerPool;

    public DataFiller(PersonRepository personRepository, NotesRepository notesRepository, PersonRepositoryCustom personRepositoryCustom, Scheduler workerPool) {
        this.personRepository = personRepository;
        this.notesRepository = notesRepository;
        this.workerPool = workerPool;
        this.personRepositoryCustom = personRepositoryCustom;
    }

    @Override
    public void run(ApplicationArguments args) {
        personRepository.saveAll(Arrays.asList(
                new Person("Pushkin", 22),
                new Person("Lermontov", 22),
                new Person("Tolstoy", 60)
        )).publishOn(workerPool)
                .subscribe(savedPerson -> {
                    logger.info("saved person:{}", savedPerson);
                    notesRepository.saveAll(Arrays.asList(
                                    new Notes(null, "txt_1_" + savedPerson.getId(), savedPerson.getId()),
                                    new Notes(null, "txt_2_" + savedPerson.getId(), savedPerson.getId())))
                            .publishOn(workerPool)
                            .subscribe(savedNotes -> logger.info("saved notes:{}", savedNotes));
                });

        personRepositoryCustom.findAll()
                .publishOn(workerPool)
                .subscribe(personDto -> logger.info("personDto:{}", personDto));
    }
}
