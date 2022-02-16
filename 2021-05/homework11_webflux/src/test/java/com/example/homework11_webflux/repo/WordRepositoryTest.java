package com.example.homework11_webflux.repo;

import com.example.homework11_webflux.model.Word;
import com.example.homework11_webflux.model.WordState;
import com.example.homework11_webflux.repository.DictionaryRepository;
import com.example.homework11_webflux.repository.WordRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
public class WordRepositoryTest {
    @Autowired
    private WordRepository repository;

    @Autowired
    private DictionaryRepository dictRepository;

    @Test
    public void shouldSetIdOnSave() {
        Mono<Word> wordMono = repository.save(
                new Word("7", "test",  "тест", "1", "just test",
                        LocalDate.of(2022, 02, 12),
                        LocalDate.of(2022, 02, 10),
                        WordState.NEW, 0
                        //,dictRepository.findById("1")
                        ));

        StepVerifier
                .create(wordMono)
                .assertNext(word -> assertNotNull(word.getId()))
                .expectComplete()
                .verify();
    }
}
