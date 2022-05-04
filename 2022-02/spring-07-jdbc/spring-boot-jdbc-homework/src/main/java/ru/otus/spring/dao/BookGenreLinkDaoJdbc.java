package ru.otus.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.BookGenreLink;
import ru.otus.spring.mapper.BookGenreLinkMapper;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BookGenreLinkDaoJdbc implements BookGenreLinkDao {

    private final NamedParameterJdbcOperations jdbc;
    private final BookGenreLinkMapper bookGenreLinkMapper;

    @Override
    public List<BookGenreLink> getAll() {
        return jdbc.query("select id, book_id, genre_id from book_genre_link", bookGenreLinkMapper);
    }

    @Override
    public List<BookGenreLink> getAllByBookId(long id) {
        try {
            return jdbc.query("select id, book_id, genre_id from book_genre_link where book_id = :id", Collections.singletonMap("id", id), bookGenreLinkMapper);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    public List<BookGenreLink> getAllByGenreId(long id) {
        try {
            return jdbc.query("select id, book_id, genre_id from book_genre_link where genre_id = :id", Collections.singletonMap("id", id), bookGenreLinkMapper);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    public void insert(long bookId, long genreId) {
        jdbc.update("insert into book_genre_link (book_id, genre_id) values (:bookId, :genreId)", Map.of("bookId", bookId, "genreId", genreId));
    }

    @Override
    public void deleteById(long id) {
        jdbc.update("delete from book_genre_link where id = :id", Collections.singletonMap("id", id));
    }
}
