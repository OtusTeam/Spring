package ru.otus.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.FullBookInfo;
import ru.otus.spring.mapper.FullBookInfoExtractor;

import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FullBookInfoDaoJdbc implements FullBookInfoDao {

    private final NamedParameterJdbcOperations jdbc;
    private final FullBookInfoExtractor fullBookInfoExtractor;

    @Override
    public List<FullBookInfo> getAll() {
        return jdbc.query("select b.id BOOK_ID, b.name BOOK_NAME, a.id, a.name, 0 TYPE from book_author_link bal " +
                "join book b on bal.book_id = b.id " +
                "join author a on bal.author_id = a.id " +
                "union all " +
                "select b.id BOOK_ID, b.name BOOK_NAME, g.id, g.name, 1 TYPE from book_genre_link bgl " +
                "join book b on bgl.book_id = b.id " +
                "join genre g on bgl.genre_id = g.id", fullBookInfoExtractor);
    }

    @Override
    public FullBookInfo getById(long id) {
        try {
            return jdbc.query("select b.id BOOK_ID, b.name BOOK_NAME, a.id, a.name, 0 TYPE from book_author_link bal " +
                    "join book b on bal.book_id = b.id " +
                    "join author a on bal.author_id = a.id " +
                    "WHERE b.id = :id " +
                    "union all " +
                    "select b.id BOOK_ID, b.name BOOK_NAME, g.id, g.name, 1 TYPE from book_genre_link bgl " +
                    "join book b on bgl.book_id = b.id " +
                    "join genre g on bgl.genre_id = g.id " +
                    "WHERE b.id = :id", Collections.singletonMap("id", id), fullBookInfoExtractor).stream().findFirst().orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public FullBookInfo getByName(String name) {
        try {
            return jdbc.query("select b.id BOOK_ID, b.name BOOK_NAME, a.id, a.name, 0 TYPE from book_author_link bal " +
                    "join book b on bal.book_id = b.id " +
                    "join author a on bal.author_id = a.id " +
                    "WHERE b.name = :name " +
                    "union all " +
                    "select b.id BOOK_ID, b.name BOOK_NAME, g.id, g.name, 1 TYPE from book_genre_link bgl " +
                    "join book b on bgl.book_id = b.id " +
                    "join genre g on bgl.genre_id = g.id " +
                    "WHERE b.name = :name", Collections.singletonMap("name", name), fullBookInfoExtractor).stream().findFirst().orElse(null);
        } catch (Exception e) {
            return null;
        }
    }
}
