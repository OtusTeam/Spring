package ru.otus.spring.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoConfig {

	@Bean
	Object getObject() {
		Object o = new Object();
		System.out.println("Create new: " + o);
		return o;
	}

	@Bean
	PersonDao getPersonDao1() {
		return new PersonDaoObject(this.getObject());
	}

	@Bean
	PersonDao getPersonDao2() {
		return new PersonDaoObject(this.getObject());
	}
}
