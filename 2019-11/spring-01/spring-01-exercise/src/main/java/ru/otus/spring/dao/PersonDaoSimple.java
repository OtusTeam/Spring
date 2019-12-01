package ru.otus.spring.dao;

import ru.otus.spring.domain.Person;

public class PersonDaoSimple implements PersonDao {
    private Integer defaultAge;

    public void setDefaultAge(Integer defaultAge) {
        this.defaultAge = defaultAge;
    }

    public Person findByName(String name) {
        return new Person(name, defaultAge != null ? defaultAge : 18);
    }
}
