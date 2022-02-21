package com.example.homework11_webflux.controller;


import com.example.homework11_webflux.exception.WordNotFoundException;
import com.example.homework11_webflux.model.Word;
import com.example.homework11_webflux.repository.WordRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class WordController {
    private WordRepository repository;

    public WordController(WordRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/words")
    public Flux<Word> getAllWords() {
        return repository.findAll();
    }

    @GetMapping("/words/{id}")
    public Mono<Word> getById(@PathVariable String id) {
        return repository.findById(id);
    }

    @PostMapping("/words")
    public Mono<Word> createWord(@RequestBody Mono<Word> word) {
        return repository.save(word);
    }

    @DeleteMapping("/words/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<Void> deleteWord(@PathVariable String id) {
        return repository.deleteById(id);
    }

    @PutMapping("/words/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Word> updateWord(@PathVariable String id, @RequestBody Mono<Word> newWord){
        return repository.findById(id).
                map(w -> newWord).
                flatMap(repository::save).
                switchIfEmpty(Mono.error(new WordNotFoundException("Not found word with id = " + id)));

    }
}
