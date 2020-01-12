package ru.otus.work.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import ru.otus.work.domain.CommentBook;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Тест класса CommentBookJpaImpl")
@ActiveProfiles("test")
public class CommentBookJpaImplTest {

    @Autowired
    CommentBookJpa commentBookJpa;

    @Test
    @DisplayName("Проверка добавления")
    public void insertTest() {
        Long bookId = 1L;
        String text = "Text";

        CommentBook commentBook = CommentBook.builder()
                .bookId(bookId)
                .text(text)
                .build();

        CommentBook commentBook2 = CommentBook.builder()
                .bookId(bookId)
                .text(text)
                .build();

        commentBookJpa.save(commentBook);
        commentBookJpa.save(commentBook2);

        List<CommentBook> commentBooks = commentBookJpa.findByBookId(bookId);
        assertThat(commentBooks.size()).isGreaterThan(2);
        assertThat(commentBooks.get(0).getBookId()).isEqualTo(bookId);
        assertThat(commentBooks.get(0).getText()).isEqualTo(text);
    }
}
