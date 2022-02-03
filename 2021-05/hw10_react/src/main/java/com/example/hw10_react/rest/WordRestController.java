package com.example.hw10_react.rest;

import com.example.hw10_react.model.Word;
import com.example.hw10_react.model.WordState;
import com.example.hw10_react.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;

@RestController
public class WordRestController {
    private final WordRepository repository;

    @Autowired
    public WordRestController(WordRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/words")
    public List<Word> getAllWords() {
        return repository.findAll();
    }

    @PostMapping("/api/words")
    public ResponseEntity createWord(@RequestBody Word word) throws URISyntaxException {
        Word newWord = repository.save(word);
        return ResponseEntity.created(new URI("/api/words/"+newWord.getId())).body(newWord);
    }

    @DeleteMapping("/api/words/{id}")
    public ResponseEntity deleteWord(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/words/{id}")
    public ResponseEntity updateWord(@PathVariable Long id, @RequestBody Word newWord){
        Word word = repository.findById(id).orElseThrow(RuntimeException::new);
        word.setName(newWord.getName());
        word.setTranslation(newWord.getTranslation());
        word.setContext(newWord.getContext());
        word.setAddedDate(LocalDate.now());
        word.setExample(newWord.getExample());
        word.setDictionary(newWord.getDictionary());
        word.setState(WordState.NEW);
        word = repository.save(word);

        return ResponseEntity.ok(word);
    }

}
