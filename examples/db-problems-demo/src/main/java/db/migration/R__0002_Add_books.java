package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class R__0002_Add_books extends BaseJavaMigration {

    public static final int BOOKS_COUNT = 100_000;

    @Override
    public void migrate(Context context) throws Exception {
        DataSource ds = new SingleConnectionDataSource(context.getConnection(), true);
        NamedParameterJdbcOperations jdbc = new NamedParameterJdbcTemplate(ds);

        insertBooks(jdbc);
        insertBooksRelations(jdbc);
    }

    private void insertBooks(NamedParameterJdbcOperations jdbc) {
        List<Map<String, Object>> params = new ArrayList<>(BOOKS_COUNT);
        for (int i = 0; i < BOOKS_COUNT; i++) {
            String bookTitle = String.format("Заголовок книги #%d", i);
            String bookTextPart = String.format("Это текст книги #%d", i);
            String bookText = bookTextPart.repeat( 8000 / bookTextPart.length());
            params.add(new MapSqlParameterSource("title", bookTitle)
                    .addValue("bookText", bookText)
                    .getValues()
            );
        }
        jdbc.batchUpdate("insert into books (title, book_text) values (:title, :bookText)",
                params.toArray(new Map[0]));
    }

    private void insertBooksRelations(NamedParameterJdbcOperations jdbc) {
        List<Map<String, Object>> paramsForAuthors = new ArrayList<>(BOOKS_COUNT);
        List<Map<String, Object>> paramsForGenres = new ArrayList<>(BOOKS_COUNT);
        for (int i = 1; i <= BOOKS_COUNT; i++) {
            for (int j = 1; j <= 3; j++) {
                paramsForAuthors.add(new MapSqlParameterSource("bookId", i).addValue("authorId", j).getValues());
                paramsForGenres.add(new MapSqlParameterSource("bookId", i).addValue("genreId", j).getValues());
            }
        }
        jdbc.batchUpdate("insert into books_authors (book_id, author_id) values (:bookId, :authorId)",
                paramsForAuthors.toArray(new Map[0]));

        jdbc.batchUpdate("insert into books_genres (book_id, genre_id) values (:bookId, :genreId)",
                paramsForGenres.toArray(new Map[0]));
    }

}
