package ru.otus.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BookAuthorLinkDaoJdbc implements BookAuthorLinkDao {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public void insert(long bookId, long authorId) {
        jdbc.update("insert into book_author_link (book_id, author_id) values (:bookId, :authorId)", Map.of("bookId", bookId, "authorId", authorId));
    }

    @Override
    public void deleteAllByBookId(long bookId) {
        jdbc.update("delete from book_author_link where book_id = (select id from book where id = :bookId)", Collections.singletonMap("bookId", bookId));
    }

}
