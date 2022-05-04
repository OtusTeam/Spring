package ru.otus.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.*;
import ru.otus.spring.mapper.BookMapper;

import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations jdbc;
    private final BookMapper bookMapper;

    @Override
    public List<Book> getAll() {
        return jdbc.query("select id, name from book", bookMapper);
    }

    @Override
    public Book getById(long id) {
        try {
            return jdbc.queryForObject("select id, name from book where id = :id", Collections.singletonMap("id", id), bookMapper);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Book getByName(String name) {
        try {
            return jdbc.queryForObject("select id, name from book where name = :name", Collections.singletonMap("name", name), bookMapper);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void insert(String name) {
        jdbc.update("insert into book (name) values (:name)", Collections.singletonMap("name", name));
    }

    @Override
    public void deleteById(long id) {
        jdbc.update("delete from book where id = :id", Collections.singletonMap("id", id));
    }
}
