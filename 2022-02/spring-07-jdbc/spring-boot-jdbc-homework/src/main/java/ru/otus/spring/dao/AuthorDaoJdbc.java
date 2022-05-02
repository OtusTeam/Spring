package ru.otus.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.mapper.AuthorMapper;

import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations jdbc;
    private final AuthorMapper authorMapper;

    @Override
    public List<Author> getAll() {
        return jdbc.query("select id, name from author", authorMapper);
    }

    @Override
    public Author getById(long id) {
        try {
            return jdbc.queryForObject("select id, name from author where id = :id", Collections.singletonMap("id", id), authorMapper);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Author getByName(String name) {
        try {
            return jdbc.queryForObject("select id, name from author where name = :name", Collections.singletonMap("name", name), authorMapper);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void insert(String name) {
        jdbc.update("insert into author (name) values (:name)", Collections.singletonMap("name", name));
    }

    @Override
    public void deleteById(long id) {
        jdbc.update("delete from author where id = :id", Collections.singletonMap("id", id));
    }

}
