insert into authors(name)
values ('Пелевин'), ('Нил Стивенс'), ('Лафоре');

insert into genres(name)
values ('Фантастика'), ('Техническая');

insert into books(caption)
values ('ППППП'), ('iFuk'), ('Криптономикон'), ('Reamde'), ('Алгоритмы и структуры данных');

insert into books_authors(book_id, author_id)
values (1, 1), (2, 1), (3, 2), (4, 2), (5, 3);

insert into books_genres(book_id, genre_id)
values (1, 1), (2, 1), (3, 1), (4, 1), (5, 2);

insert into comments(book_id, comment)
values (1, 'Вообще непонимаю'), (3, 'Понял на 5 раз'), (3, 'Морпехи вперёд'), (4, 'Ну и закрутил');
