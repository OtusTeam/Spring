package ru.otus.spring.dao;

import java.util.List;

import ru.otus.spring.domain.Person;

public interface PersonDao {

	int count();

	void insert(Person person);

	Person getById(long id);

	List<Person> getAll();

	void deleteById(long id);
}
