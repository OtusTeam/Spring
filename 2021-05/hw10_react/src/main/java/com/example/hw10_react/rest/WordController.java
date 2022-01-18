package com.example.hw10_react.rest;

import com.example.hw10_react.model.Word;
import com.example.hw10_react.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WordController {
    private final WordRepository repository;

    @Autowired
    public WordController(WordRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/words")
    public List<Word> getAllWords() {
        return repository.findAll();
    }
}
