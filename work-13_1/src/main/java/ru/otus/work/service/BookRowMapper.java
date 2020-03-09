package ru.otus.work.service;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.work.model.jdbc.Author;
import ru.otus.work.model.jdbc.Book;
import ru.otus.work.model.jdbc.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {
    public Book mapRow(ResultSet rs, int i) throws SQLException {
        return Book.builder()
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .author(rs.getString("author") != null ? Author.builder().name(rs.getString("author")).build() : null)
                .genre(rs.getString("author") != null ? Genre.builder().name(rs.getString("genre")).build() : null)
                .build();
    }
}
