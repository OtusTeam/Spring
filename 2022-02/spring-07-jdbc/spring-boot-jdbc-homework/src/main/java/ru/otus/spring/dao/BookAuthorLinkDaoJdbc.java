package ru.otus.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.BookAuthorLink;
import ru.otus.spring.mapper.BookAuthorLinkMapper;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BookAuthorLinkDaoJdbc implements BookAuthorLinkDao {

    private final NamedParameterJdbcOperations jdbc;
    private final BookAuthorLinkMapper bookAuthorLinkMapper;

    @Override
    public List<BookAuthorLink> getAll() {
        return jdbc.query("select id, book_id, author_id from book_author_link", bookAuthorLinkMapper);
    }

    @Override
    public List<BookAuthorLink> getAllByBookId(long id) {
        try {
            return jdbc.query("select id, book_id, author_id from book_author_link where book_id = :id", Collections.singletonMap("id", id), bookAuthorLinkMapper);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    public List<BookAuthorLink> getAllByAuthorId(long id) {
        try {
            return jdbc.query("select id, book_id, author_id from book_author_link where author_id = :id", Collections.singletonMap("id", id), bookAuthorLinkMapper);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    public void insert(long bookId, long authorId) {
        jdbc.update("insert into book_author_link (book_id, author_id) values (:bookId, :authorId)", Map.of("bookId", bookId, "authorId", authorId));
    }

    @Override
    public void deleteById(long id) {
        jdbc.update("delete from book_author_link where id = :id", Collections.singletonMap("id", id));
    }

}
