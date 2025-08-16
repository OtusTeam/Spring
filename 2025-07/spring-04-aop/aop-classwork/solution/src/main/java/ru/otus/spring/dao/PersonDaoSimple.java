package ru.otus.spring.dao;

import org.springframework.stereotype.Repository;

import ru.otus.spring.domain.Person;
import ru.otus.spring.logging.LogMe;

@Repository
public class PersonDaoSimple implements PersonDao {

	@Override
	@LogMe
	public Person findByName(String name) {
		return new Person(name, 18);
	}
}
