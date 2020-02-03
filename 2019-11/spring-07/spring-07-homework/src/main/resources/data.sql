INSERT INTO `authors` (id, name) VALUES (1, 'Пелевин'), (2, 'Sierra'), (3, 'Marty');
INSERT INTO `genres` (id, name) VALUES (1, 'Фантастика'), (2, 'Tech');
INSERT INTO `books` (id, caption) VALUES (1, 'S.N.U.F.F.'), (2, 'Head First Java'), (3, 'Other book'), (4, 'Какая то книга');
INSERT INTO `books_authors` (book_id, author_id) values (1, 1), (2, 2), (3, 1), (2, 3);
INSERT INTO `books_genres` (book_id, genre_id) values (1, 1), (2, 2), (3, 1);

