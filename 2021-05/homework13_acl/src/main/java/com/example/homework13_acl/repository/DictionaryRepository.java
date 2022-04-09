package com.example.homework13_acl.repository;

import com.example.homework13_acl.model.Dictionary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PostFilter;

import java.util.List;


public interface DictionaryRepository extends CrudRepository<Dictionary, Long> {

    @PostFilter("hasPermission(filterObject, 'READ')")
    List<Dictionary> findAll();

}
