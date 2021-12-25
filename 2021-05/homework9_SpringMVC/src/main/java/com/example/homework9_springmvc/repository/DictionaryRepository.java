package com.example.homework9_springmvc.repository;

import com.example.homework9_springmvc.model.Dictionary;
import org.springframework.data.repository.CrudRepository;

public interface DictionaryRepository extends CrudRepository<Dictionary, Long> {

}
