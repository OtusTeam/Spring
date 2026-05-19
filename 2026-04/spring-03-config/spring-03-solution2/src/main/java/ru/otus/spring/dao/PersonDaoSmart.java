package ru.otus.spring.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Person;

//@Primary
@Repository
public class PersonDaoSmart implements PersonDao {

    public Person findByName(String name) {
        return new Person(name, 21);
    }
}
