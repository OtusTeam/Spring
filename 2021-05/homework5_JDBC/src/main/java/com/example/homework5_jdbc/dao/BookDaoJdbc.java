package com.example.homework5_jdbc.dao;

import com.example.homework5_jdbc.domain.Author;
import com.example.homework5_jdbc.domain.Book;
import com.example.homework5_jdbc.domain.Genre;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class BookDaoJdbc implements BookDao{

    private final NamedParameterJdbcOperations jdbc;

    private final AuthorDao authorDao;

    private final GenreDao genreDao;

    public BookDaoJdbc(NamedParameterJdbcOperations jdbc, AuthorDao authorDao, GenreDao genreDao) {
        this.jdbc = jdbc;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("select b.id, b.name, b.author_id, b.genre_id,\n" +
                "a.name as author_name, g.name as genre_name\n" +
                " from books b left join authors a on b.author_id = a.id\n" +
                "left join genres g on b.genre_id = g.id", new BookMapper());
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return jdbc.queryForObject("select b.id, b.name, b.author_id, b.genre_id,\n" +
                "a.name as author_name, g.name as genre_name\n" +
                " from books b left join authors a on b.author_id = a.id\n" +
                "left join genres g on b.genre_id = g.id where b.id = :id", params, new BookMapper());
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        jdbc.update("delete from books where id = :id", params);
    }

    @Override
    public void insert(Book book) {
        Author author = book.getAuthor();
        Genre genre = book.getGenre();
//        if (authorDao.getById(author.getId()) == null){
//            authorDao.insert(author);
//        }
//        if (genreDao.getById(genre.getId()) == null) {
//            genreDao.insert(genre);
//        }

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", book.getName());
        params.addValue("author", author.getId());
        params.addValue("genre", genre.getId());
        jdbc.update("insert into books (name, author_id, genre_id) values (:name, :author, :genre)",
                params);
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long authorId = resultSet.getLong("author_id");
            String authorName = resultSet.getString("author_name");
            Author author = new Author(authorId, authorName);

            long genreId = resultSet.getLong("genre_id");
            String genreName = resultSet.getString("genre_name");
            Genre genre = new Genre(genreId, genreName);

            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            Book book = new Book(id, name, author, genre);

            return book;
        }
    }

}
