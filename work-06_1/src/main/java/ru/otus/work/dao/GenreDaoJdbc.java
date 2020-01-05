package ru.otus.work.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.work.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class GenreDaoJdbc implements GenreDao {

    private static AtomicLong genId = new AtomicLong(1000);
    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public GenreDaoJdbc(JdbcOperations jdbc, NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.jdbc = jdbc;
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public int count() {
        return jdbc.queryForObject("select count(*) from genres", Integer.class);
    }

    @Override
    public Genre insert(Genre genre) {
        long id = genId.incrementAndGet();
        Map<String, Object> params = new HashMap<>(2);
        params.put("ID", id);
        params.put("NAME", genre.getName());

        namedParameterJdbcOperations.update("insert into genres (ID, NAME) values (:ID, :NAME)", params);
        genre.setId(id);
        return genre;
    }

    @Override
    public void update(Genre genre) {
        Map<String, Object> params = new HashMap<>(2);
        params.put("ID", genre.getId());
        params.put("NAME", genre.getName());

        namedParameterJdbcOperations.update("update genres set NAME = :NAME where ID = :ID", params);
    }

    @Override
    public Genre getById(Long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        try {
            return namedParameterJdbcOperations.queryForObject(
                    "select * from genres where id = :id", params, new GenreDaoJdbc.GenreMapper()
            );
        } catch (
                EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Genre getByName(String name) {
        Map<String, Object> params = Collections.singletonMap("name", name);
        try {
            return namedParameterJdbcOperations.queryForObject(
                    "select * from genres where name = :name", params, new GenreDaoJdbc.GenreMapper()
            );
        } catch (
                EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query("select * from genres", new GenreDaoJdbc.GenreMapper());
    }

    @Override
    public void deleteById(Long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from genres where id = :id", params
        );
    }

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Genre(id, name);
        }
    }
}
