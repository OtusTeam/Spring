package ru.otus.spring.event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.spring.exception.AuthorDeleteException;
import ru.otus.spring.exception.GenreDeleteException;
import ru.otus.spring.models.Author;
import ru.otus.spring.models.Book;
import ru.otus.spring.models.Genre;
import ru.otus.spring.repositories.BookRepository;

@Component
public class CascadeDeleteMongoEventListener extends AbstractMongoEventListener<Object> {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Object> event) {
        String collectionName = event.getCollectionName();
        if ("authors".equals(collectionName) || "genres".equals(collectionName)) {
            String name = String.valueOf(event.getSource().get("name"));
            List<Book> books = bookRepository.findAll();
            if ("authors".equals(collectionName) &&
                    books.stream()
                            .flatMap(
                                    b -> b.getAuthors().stream()
                                            .map(Author::getName)
                            )
                            .anyMatch(name::equals)) {
                throw new AuthorDeleteException("Сначала удалите книги");
            }
            if ("genres".equals(collectionName) &&
                    books.stream()
                            .flatMap(
                                    b -> b.getGenres().stream()
                                            .map(Genre::getName)
                            )
                            .anyMatch(name::equals)){
                throw new GenreDeleteException("Сначала удалите книги");
            }
        }
    }
}
