package ru.otus.dbproblemsdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

/*
SELECT * FROM AUTHORS;
SELECT * FROM GENRES;
SELECT * FROM BOOKS_AUTHORS ;
SELECT * FROM BOOKS_GENRES;
SELECT * FROM BOOKS;
SELECT * FROM BOOKS_ALL_INFO ;
 */

@SpringBootApplication
public class DbProblemsDemoApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(DbProblemsDemoApplication.class, args);
	}

}
