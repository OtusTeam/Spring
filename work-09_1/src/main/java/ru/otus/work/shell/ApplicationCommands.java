package ru.otus.work.shell;

import lombok.SneakyThrows;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.work.domain.Book;
import ru.otus.work.domain.CommentBook;
import ru.otus.work.service.*;

import java.io.PrintStream;
import java.util.Collections;
import java.util.List;

@ShellComponent
@Component
public class ApplicationCommands {

    private final BookService bookService;
    private final CommentBookService commentBookService;
    private final LocalizedMessage localizedMessage;

    @ShellMethod(value = "Get all books", key = {"all", "allbooks"})
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public void allBooks() {
        printAllBooks();
    }

    @ShellMethod(value = "Delete book, format d <number>", key = {"d", "delete"})
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteBook(@ShellOption String number) {
        if (number != null && number.length() > 0) {
            bookService.delete(number);
            printAllBooks();
        }
    }

    @ShellMethod(value = "Save book on format <book name> <author> <genre> <description>", key = {"s", "save"})
    @Transactional(propagation = Propagation.REQUIRED)
    public void addBook(
            @ShellOption(defaultValue = "") String name,
            @ShellOption(defaultValue = "") String authorStr,
            @ShellOption(defaultValue = "") String genreStr,
            @ShellOption(defaultValue = "") String description
    ) {
        bookService.save(
                null,
                name,
                Collections.singletonList(authorStr),
                genreStr,
                description
        );

        printAllBooks();
    }

    @ShellMethod(value = "Modify book on format <number> <book name> <author> <genre> <description>", key = {"m", "modify"})
    @Transactional(propagation = Propagation.REQUIRED)
    public void modifyBook(
            @ShellOption(defaultValue = "") String number,
            @ShellOption(defaultValue = "") String name,
            @ShellOption(defaultValue = "") String authorStr,
            @ShellOption(defaultValue = "") String genreStr,
            @ShellOption(defaultValue = "") String description
    ) {
        if (number != null && number.length() > 0) {
            Book bookByName = bookService.findById(number);
            if (bookByName == null) {
                String[] descr = {number};
                System.out.println(
                        localizedMessage.getMessage(
                                "bookNotFound",
                                descr
                        )
                );
                return;
            }

            bookService.save(
                    bookByName.getId(),
                    name,
                    Collections.singletonList(authorStr),
                    genreStr,
                    description
            );

            printAllBooks();
        }
    }

    @ShellMethod(value = "Add comment for book on format <book number> <comment>", key = {"cm", "comment"})
    @Transactional(propagation = Propagation.REQUIRED)
    public void addCommentForBook(
            @ShellOption(defaultValue = "") String number,
            @ShellOption(defaultValue = "") String comment
    ) {
        if (number != null && number.length() > 0) {
            Book bookByName = bookService.findById(number);
            if (bookByName == null) {
                String[] descr = {number};
                System.out.println(
                        localizedMessage.getMessage(
                                "bookNotFound",
                                descr
                        )
                );
                return;
            }

            bookService.addComment(
                    bookByName.getId(),
                    comment
            );

            printAllBooks();
        }
    }

    @ShellMethod(value = "All books of the author on format <author_name>", key = {"ab", "authorBook"})
    @Transactional(propagation = Propagation.REQUIRED)
    public void allBookByAuthor(
            @ShellOption(defaultValue = "") String authorName
    ) {
        List<Book> books = bookService.findByAuthor(authorName);
        System.out.println("\n==============================================");
        books.forEach(b -> {
            String[] lines =
                    {b.getId(), b.getName(),
                            b.getAuthors() != null ? b.getAuthors().toString() : "",
                            b.getGenre() != null ? b.getGenre().getName() : "",
                            b.getDescription()};

            System.out.println(
                    localizedMessage.getMessage(
                            "bookName",
                            lines
                    )
            );
        });
    }

    private void printAllBooks() {
        System.out.println(
                localizedMessage.getMessage(
                        "bookAll",
                        null
                )
        );
        System.out.println("\n==============================================");
        bookService.listAll().forEach(b -> {

            String[] descr = {b.getId(), b.getName(),
                    b.getAuthors() != null ? b.getAuthors().toString() : "",
                    b.getGenre() != null ? b.getGenre().getName() : "",
                    b.getDescription()};

            System.out.println(
                    localizedMessage.getMessage(
                            "bookName",
                            descr
                    )
            );

            System.out.println(
                    localizedMessage.getMessage(
                            "bookComments",
                            null
                    )
            );

            List<CommentBook> commentBooks = commentBookService.findByBookId(b.getId());
            commentBooks.forEach(c -> System.out.println(c.getText()));

            System.out.println("\n==============================================");
        });
    }

    @SneakyThrows
    public ApplicationCommands(BookService bookService,
                               AuthorService authorService,
                               GenreService genreService,
                               CommentBookService commentBookService, LocalizedMessage localizedMessage) {
        this.bookService = bookService;
        this.commentBookService = commentBookService;
        this.localizedMessage = localizedMessage;
        System.setOut(new PrintStream(System.out, true, "Cp1251"));
    }
}
