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
    public List<Genre> getAll() {
        return jdbc.query("select id, name from genre", genreMapper);
    }

    @Override
    public Genre getById(long id) {
        try {
            return jdbc.queryForObject("select id, name from genre where id = :id", Collections.singletonMap("id", id), genreMapper);
        } catch (Exception e) {
            return null;
        }
    }

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
    public void deleteById(long id) {
        jdbc.update("delete from genre where id = :id", Collections.singletonMap("id", id));
    }
}
