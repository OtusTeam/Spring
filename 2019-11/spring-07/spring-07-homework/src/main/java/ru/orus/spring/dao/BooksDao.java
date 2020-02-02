package ru.orus.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.orus.spring.domain.Book;

@Repository
public class BooksDao {
    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedJdbc;

    public BooksDao(NamedParameterJdbcOperations namedJdbc) {
        this.namedJdbc = namedJdbc;
        this.jdbc = namedJdbc.getJdbcOperations();
    }

    public List<Book> getAll() {
        String selectSql = "SELECT id , caption from books;";
        return jdbc.query(selectSql, new BookMapper());
    }

    public String bookInfo(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        String caption = namedJdbc.queryForObject("SELECT caption from books WHERE id = :id", params, String.class);
        String authors = namedJdbc.queryForObject("" +
                "select group_concat(a.name) from books as b " +
                "left join books_authors as ba on b.id = ba.book_id " +
                "left join authors as a on ba.author_id = a.id where b.id = :id", params, String.class);
        String genres = namedJdbc.queryForObject("" +
                "select group_concat(g.name) from books as b " +
                "left join books_genres as bg on b.id = bg.book_id " +
                "left join genres as g on bg.genre_id = g.id where b.id = :id", params, String.class);
        final StringBuilder sb = new StringBuilder("");
        sb.append("Название книги: ").append(caption != null ? caption : "нет").append("\n");
        sb.append("Автор(ы): ").append(authors != null ? authors : "нет").append("\n");
        sb.append("Жанр(ы): ").append(genres != null ? genres : "нет").append("\n");
        return sb.toString();
    }

    public void addBook(String caption) {
        Map<String, Object> params = Collections.singletonMap("caption", caption);
        namedJdbc.update("insert into books (caption) value (:caption)", params);
    }

    public void deleteBook(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedJdbc.update("delete from books where id = :id", params);
    }

    public void addBookAuthor(long bookId, long authorId) {
        Map<String, Object> params = Map.of("bookId", bookId, "authorId", authorId);
        namedJdbc.update("insert into books_authors (book_id, author_id) value (:bookId, :authorId)", params);
    }

    public void deleteBookAuthor(long bookId, long authorId) {
        Map<String, Object> params = Map.of("bookId", bookId, "authorId", authorId);
        namedJdbc.update("delete from books_authors where book_id = :bookId and author_id = :authorId", params);
    }


    public void addBookGenre(long bookId, long genreId) {
        Map<String, Object> params = Map.of("bookId", bookId, "genreId", genreId);
        namedJdbc.update("insert into books_genres (book_id, genre_id) value (:bookId, :genreId)", params);
    }

    public void deleteBookGenre(long bookId, long genreId) {
        Map<String, Object> params = Map.of("bookId", bookId, "genreId", genreId);
        namedJdbc.update("delete from books_genres where book_id = :bookId and genre_id = :genreId", params);
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            Long id = resultSet.getLong("id");
            String caption = resultSet.getString("caption");
            return new Book(id, caption);
        }
    }
}
