package ru.otus.hw.repositories;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.otus.hw.models.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GenreRepositoryJdbc implements GenreRepository {

    @Override
    public List<Genre> findAll() {
        return new ArrayList<>();
    }

    @Override
    public List<Genre> findAllByIds(List<Long> ids) {
        return new ArrayList<>();
    }

    private static class GnreRowMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet rs, int i) throws SQLException {
            return null;
        }
    }
}
