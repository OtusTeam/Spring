DELETE FROM comments;
DELETE FROM books;
DELETE FROM authors;
DELETE FROM genres;

insert into authors (name) values ('Arthur Clarke'),
                                  ('Gustave Flaubert'),
                                  ('Marcel Proust'),
                                  ('Herbert Wells');

insert into genres (name) values ('science fiction'), ('novel');

insert into books (title, author_id, genre_id) values ('A Space Odyssey', 3, 3),
                                                       ('Sentimental Education', 4, 4),
                                                       ('In Search of Lost Time', 5, 4),
                                                       ('The Magic Shop', 6, 3);

 insert into comments (book_id, text, user_name) values (3, 'Very interesting Clarke book', 'Peter'),
                                                        (4, 'Wonderful Flaubert novel', 'Antonie');
