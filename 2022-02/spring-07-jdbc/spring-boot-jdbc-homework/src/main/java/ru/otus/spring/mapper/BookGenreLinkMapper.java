package ru.otus.spring.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.otus.spring.domain.BookGenreLink;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BookGenreLinkMapper implements RowMapper<BookGenreLink> {

    @Override
    public BookGenreLink mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new BookGenreLink(
                rs.getLong("id"),
                rs.getLong("book_id"),
                rs.getLong("genre_id")
        );
    }
}
