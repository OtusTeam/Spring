package ru.otus.spring.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
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
        Integer count = jdbc.queryForObject("select count(*) from persons", Integer.class);
        return count == null? 0: count;
    }

    @Override
    public void insert(Person person) {
        jdbc.update("insert into persons (id, name) values (?, ?)", person.getId(), person.getName());
    }

    @Override
    public Person getById(long id) {
        return jdbc.queryForObject("select id, name from persons where id = ?", new PersonMapper(), id);
    }

    @Override
    public List<Person> getAll() {
        return jdbc.query("select id, name from persons", new PersonMapper());
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
