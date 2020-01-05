package ru.otus.work.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.work.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    private static AtomicLong genId = new AtomicLong(1000);

    public AuthorDaoJdbc(JdbcOperations jdbc, NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.jdbc = jdbc;
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public int count() {
        return jdbc.queryForObject("select count(*) from authors", Integer.class);
    }

    @Override
    public Author insert(Author author) {
        long id = genId.incrementAndGet();
        Map<String, Object> params = new HashMap<>(4);
        params.put("ID", id);
        params.put("NAME", author.getName());
        params.put("BIRTH_YAR", author.getBirthYar());
        params.put("DESCRIPTION", author.getDescription());

        namedParameterJdbcOperations.update("insert into AUTHORS (ID, NAME, BIRTH_YAR, DESCRIPTION) values (:ID, :NAME, :BIRTH_YAR, :DESCRIPTION)", params);
        author.setId(id);
        return author;
    }

    @Override
    public void update(Author author) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("ID", author.getId());
        params.put("NAME", author.getName());
        params.put("BIRTH_YAR", author.getBirthYar());
        params.put("DESCRIPTION", author.getDescription());

        namedParameterJdbcOperations.update("update AUTHORS set NAME = :NAME, BIRTH_YAR = :BIRTH_YAR, DESCRIPTION = :DESCRIPTION where ID = :ID", params);
    }

    @Override
    public Author getById(Long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        try {
            return namedParameterJdbcOperations.queryForObject(
                    "select * from authors where id = :id", params, new AuthorMapper()
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Author getByName(String name) {
        Map<String, Object> params = Collections.singletonMap("name", name);
        try {
            return namedParameterJdbcOperations.queryForObject(
                    "select * from authors where name = :name", params, new AuthorMapper()
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query("select * from authors", new AuthorMapper());
    }

    @Override
    public void deleteById(Long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from authors where id = :id", params
        );
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            Date birthYar = resultSet.getDate("birth_yar");
            String description = resultSet.getString("description");
            return new Author(id, name, birthYar, description);
        }
    }
}
