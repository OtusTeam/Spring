package ru.otus.spring.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Person;


@Repository
public class PersonDaoJdbc implements PersonDao {

    private final JdbcOperations jdbc;

    public PersonDaoJdbc(JdbcOperations jdbcOperations)
    {
        this.jdbc = jdbcOperations;
    }

    @Override
    public int count() {
        Integer count = jdbc.queryForObject("select count(*) from persons", Integer.class);
        return count == null? 0: count;
    }

    @Override
    public void insert(Person person) {
        jdbc.update("insert into persons (id, name) values (?, ?)", person.getId(), person.getName());
    }

}
