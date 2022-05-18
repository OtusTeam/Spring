package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.service.LibraryService;

import static java.lang.System.out;

@ShellComponent
@RequiredArgsConstructor
public class LibraryComponent {

    private final LibraryService libraryService;

    private String userName;

    @ShellMethod(value = "Login command", key = {"l", "login"})
    public String login(@ShellOption(value = "u") String userName) {
        this.userName = userName;
        return String.format("Добро пожаловать: %s", userName);
    }

    @ShellMethod(value = "Get all books", key = {"b", "books", "allBooks", "getAllBooks"})
    @ShellMethodAvailability(value = "isUserNameExist")
    public void getAllBooks() {
        out.println(libraryService.getAllBooks());
    }

    @ShellMethod(value = "Get all only name books", key = {"names", ""})
    @ShellMethodAvailability(value = "isUserNameExist")
    public void getAllOnlyBooksName() {
        out.println(libraryService.getAllBookName());
    }

    @ShellMethod(value = "Get book by id", key = {"bbi", "bookById", "getBookById"})
    @ShellMethodAvailability(value = "isUserNameExist")
    public void getBookById(@ShellOption long id) {
        out.println(libraryService.getBookById(id));
    }

    @ShellMethod(value = "Get book by name", key = {"bbn", "bookByName", "getBookByName"})
    @ShellMethodAvailability(value = "isUserNameExist")
    public void getBookByName(@ShellOption String name) {
        out.println(libraryService.getBookByName(name));
    }

    @ShellMethod(value = "Add new Book", key = {"n", "new", "newBook", "addNewBook"})
    @ShellMethodAvailability(value = "isUserNameExist")
    public void addNewBook(@ShellOption(value = "-b") String bookName, @ShellOption(value = "-a") String authorName, @ShellOption(value = "-g") String genreName, @ShellOption(value = "-c") String commentText) {
        out.println(libraryService.addNewBook(bookName, authorName, genreName, commentText));
    }

    @ShellMethod(value = "Update book name by id", key = {"u", "update", "updateBook", "updateBookNameById"})
    @ShellMethodAvailability(value = "isUserNameExist")
    public void updateBookNameById(@ShellOption(value = "-i") long bookId, @ShellOption(value = "-b") String bookName) {
        libraryService.updateBookNameById(bookId, bookName);
    }

    @ShellMethod(value = "Delete book by id", key = {"dbbi", "deleteBookById"})
    @ShellMethodAvailability(value = "isUserNameExist")
    public void deleteBookById(@ShellOption long id) {
        libraryService.deleteBookById(id);
    }

    @ShellMethod(value = "Delete book by name", key = {"dbbn", "deleteBookByName"})
    @ShellMethodAvailability(value = "isUserNameExist")
    public void deleteBookByName(@ShellOption String name) {
        libraryService.deleteBookByName(name);
    }

    private Availability isUserNameExist() {
        return userName == null ? Availability.unavailable("Сначала залогиньтесь") : Availability.available();
    }
}
