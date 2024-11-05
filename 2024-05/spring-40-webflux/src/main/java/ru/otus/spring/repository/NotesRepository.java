package ru.otus.spring.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import ru.otus.spring.domain.Notes;


public interface NotesRepository extends ReactiveCrudRepository<Notes, Long> {

    Flux<Notes> findByPersonId(@NotNull Long personId);
}
