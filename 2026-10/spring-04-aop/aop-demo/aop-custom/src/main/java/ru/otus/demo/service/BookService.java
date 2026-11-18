package ru.otus.demo.service;

import ru.otus.demo.domain.Book;

public interface BookService {

	Book getByTitle(String title);
}
