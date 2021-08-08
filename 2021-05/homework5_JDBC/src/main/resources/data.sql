DELETE FROM authors;
DELETE FROM books;
DELETE FROM genres;
ALTER SEQUENCE book_id_seq RESTART WITH 1;
ALTER SEQUENCE author_id_seq RESTART WITH 1;
ALTER SEQUENCE genre_id_seq RESTART WITH 1;

insert into authors (id, name) values (1, 'Ray Bradbury');
insert into authors (id, name) values (2, 'Robert Heinlein');

insert into genres (id, name) values (1, 'science fiction');
insert into genres (id, name) values (2, 'novel');

insert into books (id, name, author_id, genre_id)
values (1, 'dandelion wine', 1, 2);
insert into books (id, name, author_id, genre_id)
values (2, 'door into summer', 2, 1);
