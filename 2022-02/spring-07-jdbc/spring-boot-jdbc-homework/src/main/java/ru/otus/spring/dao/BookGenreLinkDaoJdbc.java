package ru.otus.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BookGenreLinkDaoJdbc implements BookGenreLinkDao {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public void insert(long bookId, long genreId) {
        jdbc.update("insert into book_genre_link (book_id, genre_id) values (:bookId, :genreId)", Map.of("bookId", bookId, "genreId", genreId));
    }

    @Override
    public void deleteAllByBookId(long bookId) {
        jdbc.update("delete from book_genre_link where book_id = (select id from book where id = :bookId)", Collections.singletonMap("bookId", bookId));
    }
}
