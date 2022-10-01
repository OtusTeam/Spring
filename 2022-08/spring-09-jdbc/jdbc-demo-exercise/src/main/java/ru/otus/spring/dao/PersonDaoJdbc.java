package ru.otus.spring.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Person;

import java.util.List;

@Repository
public class PersonDaoJdbc implements PersonDao {
    private final JdbcOperations jdbc;

    public PersonDaoJdbc(JdbcOperations jdbcOperations)
    {
        this.jdbc = jdbcOperations;
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public void insert(Person person) {

    }

    @Override
    public Person getById(long id) {
        return null;
    }

    @Override
    public List<Person> getAll() {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }
}
