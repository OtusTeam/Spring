package ru.otus.work.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import ru.otus.work.domain.Book;
import ru.otus.work.service.BookService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Тест ACL")
@ActiveProfiles("test")
public class ACLTest {
    @Autowired
    private BookService bookService;

    @Test
    @WithMockUser(username = "admin")
    public void returnCountBooksByAdmin() {
        List<Book> books = bookService.listAll();
        assertThat(books.size()).isEqualTo(3);
    }

    @Test
    @WithMockUser(username = "user")
    public void returnCountBooksByUser() {
        List<Book> books = bookService.listAll();
        assertThat(books.size()).isEqualTo(2);
    }

    @Test
    @WithMockUser(username = "admin")
    public void findByIdBooksByAdmin() {
        Book book = bookService.findById(1L);
        assertThat(book).isNotNull();
    }

    @Test
    @WithMockUser(username = "admin")
    public void findByIdBooksByAdmin2() {
        Assertions.assertThrows(AccessDeniedException.class, () -> bookService.findById(5L))
    }

    @Test
    @WithMockUser(username = "user")
    public void saveBook() {
        Assertions.assertThrows(AccessDeniedException.class, () -> bookService.modify(
                "1",
                "name",
                "author",
                "genre",
                "description"
        ));
    }
}
