package ru.otus.demo.dao;

import ru.otus.demo.domain.Book;

public interface BookDao {

	Book findByTitle(String title);
}
