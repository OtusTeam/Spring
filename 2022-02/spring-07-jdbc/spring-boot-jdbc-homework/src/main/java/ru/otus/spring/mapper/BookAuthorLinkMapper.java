package ru.otus.spring.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.otus.spring.domain.BookAuthorLink;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BookAuthorLinkMapper implements RowMapper<BookAuthorLink> {

    @Override
    public BookAuthorLink mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new BookAuthorLink(
                rs.getLong("id"),
                rs.getLong("book_id"),
                rs.getLong("author_id")
        );
    }
}
