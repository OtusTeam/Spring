package com.example.hw10_react.repository;


import com.example.hw10_react.model.Dictionary;
import com.example.hw10_react.model.Word;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WordRepository extends CrudRepository<Word, Long> {
    List<Word> findAll();
}
