package ru.otus.spring.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repositories.BookRepository;

import java.util.Collections;
import java.util.List;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "dropDb", author = "galeevki", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertBooks", author = "galeevki")
    public void insertBooks(BookRepository repository) {
        repository.save(new Book("Сильмариллион", List.of(new Comment("Комментарий 1 к книге Сльмариллион"), new Comment("Комментарий 2 к книге Сльмариллион")), List.of(new Author("Джон Р.Р. Толкин")), List.of(new Genre("Фэнтези"))));
        repository.save(new Book("Властелин Колец", Collections.EMPTY_LIST, List.of(new Author("Джон Р.Р. Толкин")), List.of(new Genre("Фэнтези"))));
        repository.save(new Book("Гарри Поттер и Философский камень", Collections.EMPTY_LIST, List.of(new Author("Дж. К. Роулинг")), List.of(new Genre("Фэнтези"))));
        repository.save(new Book("Sapiens. Краткая история человечества", Collections.EMPTY_LIST, List.of(new Author("Ю.Н. Харари")), List.of(new Genre("Нон-фикшн"))));
        repository.save(new Book("Убийство в «Восточном экспрессе»", Collections.EMPTY_LIST, List.of(new Author("Агата Кристи")), List.of(new Genre("Детектив"))));
        repository.save(new Book("Горе от ума", Collections.EMPTY_LIST, List.of(new Author("Грибоедов А.С.")), List.of(new Genre("Комедия"))));
        repository.save(new Book("Война и мир", Collections.EMPTY_LIST, List.of(new Author("Толстой Л.Н.")), List.of(new Genre("Любовный роман"), new Genre("Исторический роман"), new Genre("Военная проза"))));
        repository.save(new Book("Над пропостью во ржи", Collections.EMPTY_LIST, List.of(new Author("Д. Сэлинджер")), List.of(new Genre("Роман воспитания"))));
        repository.save(new Book("Мифы Древней Греции", Collections.EMPTY_LIST, List.of(new Author("Н.Кун")), List.of(new Genre("Мифы и легенды"))));
        repository.save(new Book("Евгений Онегин", Collections.EMPTY_LIST, List.of(new Author("Пушкин А.С.")), List.of(new Genre("Роман в стихах"))));
        repository.save(new Book("1984", Collections.EMPTY_LIST, List.of(new Author("Д. Оруэлл")), List.of(new Genre("Научная фантастика"))));
        repository.save(new Book("Мы", Collections.EMPTY_LIST, List.of(new Author("Замятин Е.Н.")), List.of(new Genre("Антиутопия"), new Genre("Любовный роман"), new Genre("Научная фантастика"))));
        repository.save(new Book("Триумфальная арка", Collections.EMPTY_LIST, List.of(new Author("Эрих Мария Ремарк")), List.of(new Genre("Военная проза"), new Genre("Исторический роман"))));
        repository.save(new Book("Трое в лодке, не считая собаки", Collections.EMPTY_LIST, List.of(new Author("Дж. К. Джером")), List.of(new Genre("Юмористическая повесть"))));
        repository.save(new Book("Тихий Дон", Collections.EMPTY_LIST, List.of(new Author("Шолохов М.А.")), List.of(new Genre("Исторический роман"))));
        repository.save(new Book("Семь смертей Эвелины Хардкасал", Collections.EMPTY_LIST, List.of(new Author("С. Тёртон")), List.of(new Genre("Триллер"))));
        repository.save(new Book("Снеговик", Collections.EMPTY_LIST, List.of(new Author("Ю. Нёсбе")), List.of(new Genre("Детектив"), new Genre("Триллер"))));
        repository.save(new Book("Почти идеальные люди", Collections.EMPTY_LIST, List.of(new Author("М. Бут")), List.of(new Genre("Страноведение"))));
        repository.save(new Book("Краткая история времени", Collections.EMPTY_LIST, List.of(new Author("С. Хокинг")), List.of(new Genre("Научпоп"))));
        repository.save(new Book("Унесенные ветром", Collections.EMPTY_LIST, List.of(new Author("М. Митчелл")), List.of(new Genre("Исторический роман"))));
    }
}
