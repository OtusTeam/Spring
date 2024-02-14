package ru.otus.spring.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import ru.otus.spring.domain.Person;

@Primary
@Repository
public class PersonDaoObject implements PersonDao {
	public PersonDaoObject() {
	}

	public PersonDaoObject(Object object) {
		System.out.println("Create dao: " + this + " with " + object);
	}


	@Override
	public Person findByName(String name) {
		Person person = new Person(name, 18);
		System.out.println("Dao findByName: " + name + ", person: " + person);
		return person;
	}

	@Override
	public void save(Person p) {
		Person byName = findByName(p.getName());
		System.out.println("Dao save: " + p + ", findByName: " + byName);
	}
}
