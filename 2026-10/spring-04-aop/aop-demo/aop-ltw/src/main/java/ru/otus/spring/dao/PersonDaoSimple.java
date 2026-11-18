package ru.otus.spring.dao;

import org.springframework.stereotype.Repository;

import ru.otus.spring.domain.Person;

@Repository
public class PersonDaoSimple implements PersonDao {
	public PersonDaoSimple() {
		System.out.println(this.getClass().getClassLoader());
	}

	@Override
	public Person findByName(String name) {
		return new Person(name, 18);
	}
}
