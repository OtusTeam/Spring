package ru.otus.work.repositories;

import org.junit.jupiter.api.BeforeEach;
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

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName("Тест класса BookRepository")
@ActiveProfiles("test")
public class BookRepositoryImplTest {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    GenreRepository genreRepository;

    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public Author author;
    public Genre genre;

    @BeforeEach
    public void setUp() {
        author = Author
                .builder()
                .name(NAME)
                .birthYar(new Date())
                .description(DESCRIPTION)
                .build();
        authorRepository.save(author);

        genre = Genre
                .builder()
                .name(NAME)
                .build();
        genreRepository.save(genre);
    }

    @Test
    @DisplayName("Проверка добавления")
    public void insertTest() {
        Book book = Book
                .builder()
                .name(NAME)
                .description(DESCRIPTION)
                .authors(Collections.singletonList(author))
                .genre(genre)
                .build();

        String id = bookRepository.save(book).getId();
        Book bookById = bookRepository.findById(id).orElse(null);
        assertThat(bookById).isNotNull();
        assertThat(bookById.getId()).isEqualTo(id);
        assertThat(bookById.getName()).isEqualTo(NAME);
        assertThat(bookById.getDescription()).isEqualTo(DESCRIPTION);
        assertThat(bookById.getAuthors().get(0)).isNotNull();
        assertThat(bookById.getGenre()).isNotNull();
    }

    @Test
    @DisplayName("Проверка удаления")
    public void deleteTest() {
        Book book = Book
                .builder()
                .name(NAME)
                .description(DESCRIPTION)
                .authors(Collections.singletonList(author))
                .genre(genre)
                .build();

        String id = bookRepository.save(book).getId();
        Book bookById = bookRepository.findById(id).orElse(null);
        assertThat(bookById).isNotNull();

        bookRepository.deleteById(id);
        Book bookByIdFind = bookRepository.findById(id).orElse(null);
        assertThat(bookByIdFind).isNull();
    }

    @Test
    @DisplayName("Проверка обновления")
    public void updateTest() {
        Book book = Book
                .builder()
                .name(NAME)
                .description(DESCRIPTION)
                .authors(Collections.singletonList(author))
                .genre(genre)
                .build();

        String id = bookRepository.save(book).getId();
        Book bookById = bookRepository.findById(id).orElse(null);
        assertThat(bookById).isNotNull();

        String name = "newName";
        String description = "newDescription";
        bookById.setName(name);
        bookById.setDescription(description);

        bookRepository.save(bookById);

        bookById = bookRepository.findById(id).orElse(null);
        assertThat(bookById).isNotNull();
        assertThat(bookById.getName()).isEqualTo(name);
        assertThat(bookById.getDescription()).isEqualTo(description);
    }

    @Test
    @DisplayName("Все книги")
    public void findAllTest() {
        List<Book> books = bookRepository.findAll();
        assertThat(books.size()).isGreaterThan(0);
    }
}
