package ru.otus.work.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.work.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class BookDaoJdbc implements BookDao {
    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private static AtomicLong genId = new AtomicLong(1000);

    public BookDaoJdbc(JdbcOperations jdbc, NamedParameterJdbcOperations namedParameterJdbcOperations, AuthorDao authorDao, GenreDao genreDao) {
        this.jdbc = jdbc;
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @Override
    public int count() {
        return jdbc.queryForObject("select count(*) from books", Integer.class);
    }

    @Override
    public Long insert(Book book) {
        long id = book.getId() == null ? genId.incrementAndGet() : book.getId();

        Map<String, Object> params = new HashMap<>(5);
        params.put("ID", id);
        params.put("NAME", book.getName());
        params.put("DESCRIPTION", book.getDescription());
        params.put("AUTHOR_ID", book.getAuthor() != null ? book.getAuthor().getId() : null);
        params.put("GENRE_ID", book.getGenre() != null ? book.getGenre().getId() : null);

        namedParameterJdbcOperations.update("insert into BOOKS (ID, NAME, DESCRIPTION, AUTHOR_ID, GENRE_ID) values (:ID, :NAME, :DESCRIPTION, :AUTHOR_ID, :GENRE_ID)", params);
        return id;
    }

    @Override
    public void update(Book book) {
        Map<String, Object> params = new HashMap<>(5);
        params.put("ID", book.getId());
        params.put("NAME", book.getName());
        params.put("DESCRIPTION", book.getDescription());
        params.put("AUTHOR_ID", book.getAuthor() != null ? book.getAuthor().getId() : null);
        params.put("GENRE_ID", book.getGenre() != null ? book.getGenre().getId() : null);

        namedParameterJdbcOperations.update("update BOOKS set NAME = :NAME, DESCRIPTION = :DESCRIPTION, AUTHOR_ID = :AUTHOR_ID, GENRE_ID = :GENRE_ID  where ID = :ID", params);
    }

    @Override
    public Book getById(Long id) {
        try {
            Map<String, Object> params = Collections.singletonMap("id", id);
            return namedParameterJdbcOperations.queryForObject(
                    "select * from books where id = :id", params, new BookDaoJdbc.BookMapper()
            );
        } catch (
                EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("select * from books", new BookDaoJdbc.BookMapper());
    }

    @Override
    public void deleteById(Long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from books where id = :id", params
        );
    }

    private class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            long authorId = resultSet.getLong("author_id");
            long genreId = resultSet.getLong("genre_id");
            return new Book(id, name, description, authorDao.getById(authorId), genreDao.getById(genreId));
        }
    }
}
