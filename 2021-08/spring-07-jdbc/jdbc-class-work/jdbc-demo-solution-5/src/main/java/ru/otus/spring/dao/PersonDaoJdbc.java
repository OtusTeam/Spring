package ru.otus.spring.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
        return jdbc.queryForObject("select count(*) from persons", Integer.class);
    }

    @Override
    public void insert(Person person) {
        jdbc.update("insert into persons (id, `name`) values (?, ?)", person.getId(), person.getName());
    }

    @Override
    public Person getById(long id) {
        return jdbc.queryForObject("select * from persons where id = ?", new Object[] {id}, new PersonMapper());
    }

    @Override
    public List<Person> getAll() {
        return jdbc.query("select * from persons", new PersonMapper());
    }


    private static class PersonMapper implements RowMapper<Person> {

        @Override
        public Person mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Person(id, name);
        }
    }
}
