package ru.otus.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.mapper.GenreMapper;

import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcOperations jdbc;
    private final GenreMapper genreMapper;

    @Override
    public Genre getByName(String name) {
        try {
            return jdbc.queryForObject("select id, name from genre where name = :name", Collections.singletonMap("name", name), genreMapper);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void insert(String name) {
        jdbc.update("insert into genre (name) values (:name)", Collections.singletonMap("name", name));
    }

    @Override
    public List<Genre> getAllByBookId(long bookId) {
        return jdbc.query("select g.id, g.name from genre g " +
                "join book_genre_link bgl on g.id = bgl.genre_id " +
                "where bgl.book_id = :bookId", Collections.singletonMap("bookId", bookId), genreMapper);
    }
}
