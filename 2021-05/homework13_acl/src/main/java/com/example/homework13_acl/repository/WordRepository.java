package com.example.homework13_acl.repository;

import com.example.homework13_acl.model.Dictionary;
import com.example.homework13_acl.model.Word;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WordRepository extends CrudRepository<Word, Long> {
    List<Word> findAllByDictionary(Dictionary dictionary);
}
