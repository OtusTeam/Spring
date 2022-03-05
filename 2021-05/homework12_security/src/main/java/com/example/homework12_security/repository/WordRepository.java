package com.example.homework12_security.repository;

import com.example.homework12_security.model.Dictionary;
import com.example.homework12_security.model.Word;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WordRepository extends CrudRepository<Word, Long> {
    List<Word> findAllByDictionary(Dictionary dictionary);
}
