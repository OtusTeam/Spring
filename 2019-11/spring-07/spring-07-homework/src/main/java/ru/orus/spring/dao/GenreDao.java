package ru.orus.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.orus.spring.domain.Genre;

@Repository
public class GenreDao {
    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedJdbc;

    public GenreDao(JdbcOperations jdbc, NamedParameterJdbcOperations namedJdbc) {
        this.jdbc = jdbc;
        this.namedJdbc = namedJdbc;
    }


    public List<Genre> getAll() {
        String selectSql = "SELECT id , name from genres;";
        return jdbc.query(selectSql, new GenreMapper());
    }

    public void addGenre(String name) {
        Map<String, Object> params = Collections.singletonMap("name", name);
        namedJdbc.update("insert into genres (name) value (:name)", params);
    }

    public void deleteGenre(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedJdbc.update("delete from genres where id = :id", params);
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            Long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Genre(id, name);
        }
    }
}
