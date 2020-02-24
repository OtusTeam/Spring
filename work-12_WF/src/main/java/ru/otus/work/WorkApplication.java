package ru.otus.work;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.work.domain.Author;
import ru.otus.work.domain.Book;
import ru.otus.work.domain.Genre;
import ru.otus.work.repositories.BookRepository;

import java.util.Collections;
import java.util.Date;
import java.util.Locale;

@SpringBootApplication
@ComponentScan
public class WorkApplication {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        ApplicationContext context = SpringApplication.run(WorkApplication.class);

        BookRepository repository = context.getBean(BookRepository.class);

        Author author = Author
                .builder()
                .name("AUTHOR")
                .birthYar(new Date())
                .description("DESCRIPTION")
                .build();

        Genre genre = Genre
                .builder()
                .name("GENRE")
                .build();

        Book bookToSave = Book
                .builder()
                .name("NAME")
                .description("DESCRIPTION")
                .authors(Collections.singletonList(author))
                .genre(genre)
                .build();
        repository.save(bookToSave).subscribe(book -> System.out.println(book.getId()));
    }
}
