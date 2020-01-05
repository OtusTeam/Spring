package ru.otus.work.shell;

import lombok.SneakyThrows;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Component;
import ru.otus.work.domain.Book;
import ru.otus.work.service.AuthorService;
import ru.otus.work.service.BookService;
import ru.otus.work.service.GenreService;
import ru.otus.work.service.LocalizedMessage;

import java.io.PrintStream;

@ShellComponent
@Component
public class ApplicationCommands {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final LocalizedMessage localizedMessage;

    @ShellMethod(value = "Get all books", key = {"all", "allbooks"})
    public void allBooks() {
        printAllBooks();
    }

    @ShellMethod(value = "Delete book, format d <number>", key = {"d", "delete"})
    public void deleteBook(@ShellOption String number) {
        if (number != null && number.length() > 0) {
            bookService.delete(Long.valueOf(number));
            printAllBooks();
        }
    }

    @ShellMethod(value = "Save book on format <book name> <author> <genre> <description>", key = {"s", "save"})
    public void addBook(
            @ShellOption(defaultValue = "") String name,
            @ShellOption(defaultValue = "") String authorStr,
            @ShellOption(defaultValue = "") String genreStr,
            @ShellOption(defaultValue = "") String description
    ) {
        bookService.save(
                name,
                authorStr,
                genreStr,
                description
        );

        printAllBooks();
    }

    @ShellMethod(value = "Modify book on format <number> <book name> <author> <genre> <description>", key = {"m", "modify"})
    public void modifyBook(
            @ShellOption(defaultValue = "") String number,
            @ShellOption(defaultValue = "") String name,
            @ShellOption(defaultValue = "") String authorStr,
            @ShellOption(defaultValue = "") String genreStr,
            @ShellOption(defaultValue = "") String description
    ) {
        if (number != null && number.length() > 0) {
            Book bookByName = bookService.findById(Long.valueOf(number));
            if (bookByName == null) {
                String[] descr = {number};
                System.out.println(
                        localizedMessage.getMessage(
                                "bookNotFound",
                                descr
                        )
                );
            }

            bookService.modify(
                    number,
                    name,
                    authorStr,
                    genreStr,
                    description
            );

            printAllBooks();
        }
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

            String[] descr = {b.getId().toString(), b.getName(),
                    b.getAuthor() != null ? b.getAuthor().getName() : "",
                    b.getGenre() != null ? b.getGenre().getName() : "",
                    b.getDescription()};

            System.out.println(
                    localizedMessage.getMessage(
                            "bookName",
                            descr
                    )
            );
            System.out.println("\n==============================================");
        });
    }

    @SneakyThrows
    public ApplicationCommands(BookService bookService,
                               AuthorService authorService,
                               GenreService genreService,
                               LocalizedMessage localizedMessage) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
        this.localizedMessage = localizedMessage;
        System.setOut(new PrintStream(System.out, true, "Cp1251"));
    }
}
