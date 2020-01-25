package ru.otus.work.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.Shell;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.work.domain.Book;
import ru.otus.work.domain.CommentBook;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName("Интеграционный тест BookService")
@ActiveProfiles("test")
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private CommentBookService commentBookService;

    @Autowired
    private Shell shell;

    @Test
    @DisplayName("Тест команды добавление")
    public void shellSaveTest() {
        shell.evaluate(() -> "all");

        int oldCnt = bookService.listAll().size();

        shell.evaluate(() -> "s book author genre description");

        int cnt = bookService.listAll().size();
        assertThat(cnt).isEqualTo(oldCnt + 1);
    }

    @Test
    @DisplayName("Тест команды изменение")
    public void shellModifyTest() {
        shell.evaluate(() -> "all");

        List<Book> books = bookService.listAll();

        assertThat(books.size()).isNotZero();

        Book book = books.get(0);

        String name = "bookNew";
        String author = "authorNew";
        String genre = "genreNew";
        String description = "descriptionNew";


        shell.evaluate(() -> String.format("m %s %s %s %s %s",
                book.getId(),
                name,
                author,
                genre,
                description)
        );

        Book bookModify = bookService.findById(book.getId());
        assertThat(bookModify.getName()).isEqualTo(name);
        assertThat(bookModify.getAuthors().get(0).getName()).isEqualTo(author);
        assertThat(bookModify.getGenre().getName()).isEqualTo(genre);
        assertThat(bookModify.getDescription()).isEqualTo(description);
    }

    @Test
    @DisplayName("Тест команды удаление")
    public void shellDeleteTest() {
        List<Book> books = bookService.listAll();

        assertThat(books.size()).isNotZero();

        books.forEach(b -> shell.evaluate(() -> String.format("d %s", b.getId())));

        books = bookService.listAll();
        assertThat(books.size()).isZero();
    }

    @Test
    @DisplayName("Тест команды добавления комментария")
    public void shellAddCommentTest() {
        shell.evaluate(() -> "s book author genre description");
        List<Book> books = bookService.listAll();
        assertThat(books.size()).isNotZero();

        Book book = books.get(0);
        String comment = "comment";
        String comment2 = "comment2";
        String comment3 = "comment3";

        shell.evaluate(() -> String.format("cm %s %s",
                book.getId(),
                comment)
        );
        shell.evaluate(() -> String.format("cm %s %s",
                book.getId(),
                comment2)
        );
        shell.evaluate(() -> String.format("cm %s %s",
                book.getId(),
                comment3)
        );

        Book bookFind = bookService.findById(book.getId());
        List<CommentBook> commentBooks = commentBookService.findByBookId(bookFind.getId());
        assertThat(bookFind).isNotNull();
        assertThat(commentBooks.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    @DisplayName("Тест команды - все книги автора")
    public void shellAllBooksAuthorTest() {
        shell.evaluate(() -> "s book author genre description");

        shell.evaluate(() -> "ab author");
    }

    @Test
    @DisplayName("Показать все книги авторов по всем книгам")
    public void findAllBooks() {
        String author = "author";

        bookService.save(
                null,
                "name",
                Collections.singletonList(author),
                "genre",
                "description"
        );

        bookService.listAll().forEach(book -> {
            int booksCount = bookService.findByAuthor(author).size();
            assertThat(booksCount).isGreaterThanOrEqualTo(1);
        });
    }
}
