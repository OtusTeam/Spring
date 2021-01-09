package ru.otus.spring.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class PersonDaoJdbc implements PersonDao {
    private final JdbcOperations jdbc;

    public PersonDaoJdbc(JdbcOperations jdbcOperations)
    {
        this.jdbc = jdbcOperations;
    }


    @Override
    public int count() {
        int i = (int) jdbc.queryForObject("select count(*) from persons", Integer.class);
        return i;
    }

    @Override
    public Person getPersonById(Long id) {
        Person person = (Person) jdbc.queryForObject("select * from persons where id =?", new Object[]{id}, new RowMapper<Person>() {
            @Override
            public Person mapRow(ResultSet resultSet, int i) throws SQLException {

                long id1 = resultSet.getLong("id");
                String name = resultSet.getString("name");
                return new Person(id1, name);
            }
        });
        return person;
    }


}
