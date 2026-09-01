package ru.otus.demo.dao;

import ru.otus.demo.domain.Book;

public class BookDaoSimple implements BookDao {
	@Override
	public Book findByTitle(String title) {
		return new Book(title);
	}
}
