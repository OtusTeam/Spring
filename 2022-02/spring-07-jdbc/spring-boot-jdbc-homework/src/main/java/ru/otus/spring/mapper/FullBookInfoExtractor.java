package ru.otus.spring.mapper;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.domain.FullBookInfo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FullBookInfoExtractor implements ResultSetExtractor<List<FullBookInfo>> {

    @Override
    public List<FullBookInfo> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Long, FullBookInfo> map = new HashMap<>();
        while (rs.next()) {
            Long id = rs.getLong("BOOK_ID");
            FullBookInfo fullBookInfo = map.get(id);
            if (fullBookInfo == null) {
                fullBookInfo = new FullBookInfo();
                fullBookInfo.setId(id);
                fullBookInfo.setName(rs.getString("BOOK_NAME"));
                map.put(id, fullBookInfo);
            }

            int type = rs.getInt("TYPE");
            if (type == 0) {
                List<Author> authorList = fullBookInfo.getAuthorList();
                if (authorList == null) {
                    authorList = new ArrayList<>();
                    fullBookInfo.setAuthorList(authorList);
                }

                authorList.add(new Author(rs.getLong("ID"), rs.getString("NAME")));
            } else if (type == 1) {
                List<Genre> genreList = fullBookInfo.getGenreList();
                if (genreList == null) {
                    genreList = new ArrayList<>();
                    fullBookInfo.setGenreList(genreList);
                }

                genreList.add(new Genre(rs.getLong("ID"), rs.getString("NAME")));
            }
        }

        return new ArrayList<>(map.values());
    }
}
