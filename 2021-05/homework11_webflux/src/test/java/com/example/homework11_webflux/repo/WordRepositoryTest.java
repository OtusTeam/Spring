package com.example.homework11_webflux.repo;

import com.example.homework11_webflux.model.Word;
import com.example.homework11_webflux.model.WordState;
import com.example.homework11_webflux.repository.WordRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
public class WordRepositoryTest {
    @Autowired
    private WordRepository repository;

    @Test
    public void shouldSetIdOnSave() {
        Mono<Word> wordMono = repository.save(
                new Word("7", "test", "тест", "1", "just test",
                        LocalDate.of(2022, 02, 12),
                        LocalDate.of(2022, 02, 10),
                        WordState.NEW, 0
                ));

        StepVerifier
                .create(wordMono)
                .assertNext(word -> assertNotNull(word.getId()))
                .expectComplete()
                .verify();
    }

    @Test
    public void shouldReturnListOfWords() {

        Flux<Word> words = repository.saveAll(Arrays.asList(
                new Word("7", "test", "тест", "1", "just test",
                        LocalDate.of(2022, 02, 12),
                        LocalDate.of(2022, 02, 10),
                        WordState.NEW, 0
                ),
                new Word("1", "treadmill", "беговая дорожка", "Dr. House",
                        "Put the patient on a treadmill.", LocalDate.of(2022, 02, 10),
                        null, WordState.NEW, 1)));

        StepVerifier
                .create(words)
                .assertNext(word -> assertThat(word.getName() == "test"))
                .assertNext(word -> assertThat(word.getName() == "treadmill"))
                .expectComplete()
                .verify();
    }

    @Test
    public void shouldUpdateWordById() {

        Flux<Word> words = repository.saveAll(Arrays.asList(
                new Word("0", "test", "тест", "1", "just test",
                        LocalDate.of(2022, 02, 12),
                        LocalDate.of(2022, 02, 10),
                        WordState.NEW, 0
                ),
                new Word("1", "treadmill", "беговая дорожка", "Dr. House",
                        "Put the patient on a treadmill.", LocalDate.of(2022, 02, 10),
                        null, WordState.NEW, 1)));
        Word newWord = new Word("0", "new", "новый", "1", "just test",
                LocalDate.of(2022, 02, 12),
                LocalDate.of(2022, 02, 10),
                WordState.NEW, 0
        );

        Flux<Word> updated = words
                .flatMap(w -> {
                            if (w.getId() == "0") {
                                return repository.save(newWord);
                            } else return repository.save(w);
                        }
                );

        StepVerifier
                .create(updated)
                .assertNext(word -> assertThat(word.getName() == "new"))
                .assertNext(word -> assertThat(word.getName() == "treadmill"))
                .expectComplete()
                .verify();

    }


}
