package com.example.homework9_springmvc.repository;

import com.example.homework9_springmvc.model.Dictionary;
import com.example.homework9_springmvc.model.Word;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WordRepository extends CrudRepository<Word, Long> {
    List<Word> findAllByDictionary(Dictionary dictionary);
}