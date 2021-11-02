insert into authors (name) values ('Arthur Clarke'),
                                  ('Gustave Flaubert'),
                                  ('Marcel Proust'),
                                  ('Herbert Wells');

insert into genres (name) values ('science fiction'), ('novel');

insert into books (title, author_id, genre_id) values ('A Space Odyssey', 1, 1),
                                                      ('Sentimental Education', 2, 2),
                                                      ('In Search of Lost Time', 3, 2),
                                                      ('The Magic Shop', 4, 1);

insert into comments (book_id, text, user_name) values (1, 'Very interesting Clarke book', 'Peter'),
                                                       (2, 'Wonderful Flaubert novel', 'Antonie');