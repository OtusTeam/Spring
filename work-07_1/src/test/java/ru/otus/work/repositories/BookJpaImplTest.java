package ru.otus.work.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.work.domain.Author;
import ru.otus.work.domain.Book;
import ru.otus.work.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName("Тест класса BookDaoJdbcTest")
@ActiveProfiles("test")
public class BookJpaImplTest {

    @Autowired
    BookJpa bookJpa;

    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final Long AUTHOR_ID = 1L;
    public static final Long GENRE_ID = 1L;
    public static final Author author = Author
            .builder()
            .id(AUTHOR_ID)
            .build();
    public static final Genre genre = Genre
            .builder()
            .id(GENRE_ID)
            .build();

    @Test
    @DisplayName("Проверка добавления")
    public void insertTest() {
        Book book = Book
                .builder()
                .name(NAME)
                .description(DESCRIPTION)
                .author(author)
                .genre(genre)
                .build();

        Long id = bookJpa.save(book).getId();
        Book bookById = bookJpa.getById(id).orElse(null);
        assertThat(bookById).isNotNull();
        assertThat(bookById.getId()).isEqualTo(id);
        assertThat(bookById.getName()).isEqualTo(NAME);
        assertThat(bookById.getDescription()).isEqualTo(DESCRIPTION);
        assertThat(bookById.getAuthor()).isNotNull();
        assertThat(bookById.getGenre()).isNotNull();
    }

    @Test
    @DisplayName("Проверка удаления")
    public void deleteTest() {
        Book book = Book
                .builder()
                .name(NAME)
                .description(DESCRIPTION)
                .author(author)
                .genre(genre)
                .build();

        Long id = bookJpa.save(book).getId();
        Book bookById = bookJpa.getById(id).orElse(null);
        assertThat(bookJpa).isNotNull();

        bookJpa.deleteById(id);
        Book bookByIdFind = bookJpa.getById(id).orElse(null);
        assertThat(bookByIdFind).isNull();
    }

    @Test
    @DisplayName("Проверка обновления")
    public void updateTest() {
        Book book = Book
                .builder()
                .name(NAME)
                .description(DESCRIPTION)
                .author(author)
                .genre(genre)
                .build();

        Long id = bookJpa.save(book).getId();
        Book bookById = bookJpa.getById(id).orElse(null);
        assertThat(bookById).isNotNull();

        String name = "newName";
        String description = "newDescription";
        bookById.setName(name);
        bookById.setDescription(description);

        bookJpa.save(bookById);

        bookById = bookJpa.getById(id).orElse(null);
        assertThat(bookById).isNotNull();
        assertThat(bookById.getName()).isEqualTo(name);
        assertThat(bookById.getDescription()).isEqualTo(description);
    }
}
