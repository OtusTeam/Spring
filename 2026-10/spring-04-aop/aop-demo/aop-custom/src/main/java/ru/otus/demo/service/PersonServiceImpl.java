package ru.otus.demo.service;

import ru.otus.demo.dao.PersonDao;
import ru.otus.demo.domain.Person;

public class PersonServiceImpl implements PersonService {

	private final PersonDao dao;

	public PersonServiceImpl(PersonDao dao) {
		this.dao = dao;
	}

	@Override
	public Person getByName(String name) {
		return dao.findByName(name);
	}
}
