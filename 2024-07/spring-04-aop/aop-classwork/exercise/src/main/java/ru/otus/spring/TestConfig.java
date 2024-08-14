package ru.otus.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import ru.otus.spring.dao.PersonDaoSimple;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@Configuration
public class TestConfig {
	@Scope(value = "prototype")
	@Bean()
	Object test1() {
		final Object o = new Object();
		System.out.println("Created bean: " + o);
		return o;
	}

	@Primary
	@Bean
	PersonDaoSimple personDao2() {
		final Object o = this.test1();
		return new PersonDaoSimple(o);
	}
	@Bean
	PersonDaoSimple personDao3() {
		final Object o = this.test1();
		return new PersonDaoSimple(o);
	}
}
