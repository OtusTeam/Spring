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
import ru.orus.spring.domain.Author;

@Repository
public class AuthorDao {
    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedJdbc;

    public AuthorDao(JdbcOperations jdbc, NamedParameterJdbcOperations namedJdbc) {
        this.jdbc = jdbc;
        this.namedJdbc = namedJdbc;
    }

    public List<Author> getAll() {
        String selectSql = "SELECT id , name from authors;";
        return jdbc.query(selectSql, new AuthorDao.AuthorMapper());
    }

    public void addAuthor(String name) {
        Map<String, Object> params = Collections.singletonMap("name", name);
        namedJdbc.update("insert into authors (name) value (:name)", params);
    }

    public void deleteAuthor(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedJdbc.update("delete from authors where id = :id", params);
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            Long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Author(id, name);
        }
    }

}
