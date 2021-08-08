DELETE FROM authors;
DELETE FROM books;
DELETE FROM genres;

insert into authors (id, name) values (1, 'Peter Kolotushkin');
insert into authors (id, name) values (2, 'Vasya Podushkin');

insert into genres (id, name) values (1, 'fairy tale');
insert into genres (id, name) values (2, 'poem');

insert into books (id, name, author_id, genre_id)
values (2, 'Summer day', 1, 1);
insert into books (id, name, author_id, genre_id)
values (3, 'Three piglets', 2, 2);
