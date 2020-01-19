package ru.otus.work.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.work.domain.Author;
import ru.otus.work.domain.Book;
import ru.otus.work.domain.Genre;

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
    public static final Long AUTHOR_ID = 1L;
    public static final Long GENRE_ID = 1L;
    public Author author;
    public Genre genre;

    @BeforeEach
    public void setUp() {
        author = authorRepository.findById(AUTHOR_ID).orElse(null);
        genre = genreRepository.findById(GENRE_ID).orElse(null);
    }

    @Test
    @DisplayName("Проверка добавления")
    @Transactional
    public void insertTest() {
        Book book = Book
                .builder()
                .name(NAME)
                .description(DESCRIPTION)
                .author(author)
                .genre(genre)
                .build();

        Long id = bookRepository.save(book).getId();
        Book bookById = bookRepository.findById(id).orElse(null);
        assertThat(bookById).isNotNull();
        assertThat(bookById.getId()).isEqualTo(id);
        assertThat(bookById.getName()).isEqualTo(NAME);
        assertThat(bookById.getDescription()).isEqualTo(DESCRIPTION);
        assertThat(bookById.getAuthor()).isNotNull();
        assertThat(bookById.getGenre()).isNotNull();
    }

    @Test
    @DisplayName("Проверка удаления")
    @Transactional
    public void deleteTest() {
        Book book = Book
                .builder()
                .name(NAME)
                .description(DESCRIPTION)
                .author(author)
                .genre(genre)
                .build();

        Long id = bookRepository.save(book).getId();
        Book bookById = bookRepository.findById(id).orElse(null);
        assertThat(bookById).isNotNull();

        bookRepository.deleteById(id);
        Book bookByIdFind = bookRepository.findById(id).orElse(null);
        assertThat(bookByIdFind).isNull();
    }

    @Test
    @DisplayName("Проверка обновления")
    @Transactional
    public void updateTest() {
        Book book = Book
                .builder()
                .name(NAME)
                .description(DESCRIPTION)
                .author(author)
                .genre(genre)
                .build();

        Long id = bookRepository.save(book).getId();
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
    @DisplayName("Проверка sql запроса")
    public void findAllTest() {
        List<Book> books = bookRepository.findAll();
        assertThat(books.size()).isGreaterThan(0);
    }
}
