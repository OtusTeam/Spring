--Жанры

insert into genre(`name`) values ('Фэнтези');
insert into genre(`name`) values ('Нон-фикшн');
insert into genre(`name`) values ('Детектив');
insert into genre(`name`) values ('Комедия');
insert into genre(`name`) values ('Любовный роман');
insert into genre(`name`) values ('Исторический роман');
insert into genre(`name`) values ('Военная проза');
insert into genre(`name`) values ('Роман воспитания');
insert into genre(`name`) values ('Мифы и легенды');
insert into genre(`name`) values ('Роман в стихах');
insert into genre(`name`) values ('Научная фантастика');
insert into genre(`name`) values ('Антиутопия');
insert into genre(`name`) values ('Юмористическая повесть');
insert into genre(`name`) values ('Триллер');
insert into genre(`name`) values ('Страноведение');
insert into genre(`name`) values ('Научпоп');

--Авторы
insert into author(`name`) values ('Джон Р.Р. Толкин');
insert into author(`name`) values ('Дж. К. Роулинг');
insert into author(`name`) values ('Ю.Н. Харари');
insert into author(`name`) values ('Агата Кристи');
insert into author(`name`) values ('Грибоедов А.С.');
insert into author(`name`) values ('Толстой Л.Н.');
insert into author(`name`) values ('Д. Сэлинджер');
insert into author(`name`) values ('Н.Кун');
insert into author(`name`) values ('Пушкин А.С.');
insert into author(`name`) values ('Д. Оруэлл');
insert into author(`name`) values ('Замятин Е.Н.');
insert into author(`name`) values ('Эрих Мария Ремарк');
insert into author(`name`) values ('Дж. К. Джером');
insert into author(`name`) values ('Шолохов М.А.');
insert into author(`name`) values ('С. Тёртон');
insert into author(`name`) values ('Ю. Нёсбе');
insert into author(`name`) values ('М. Бут');
insert into author(`name`) values ('С. Хокинг');
insert into author(`name`) values ('М. Митчелл');


--Книги
insert into book(`name`) values ('Сильмариллион');
insert into book(`name`) values ('Властелин Колец');
insert into book(`name`) values ('Гарри Поттер и Философский камень');
insert into book(`name`) values ('Sapiens. Краткая история человечества');
insert into book(`name`) values ('Убийство в «Восточном экспрессе»');
insert into book(`name`) values ('Горе от ума');
insert into book(`name`) values ('Война и мир');
insert into book(`name`) values ('Над пропостью во ржи');
insert into book(`name`) values ('Мифы Древней Греции');
insert into book(`name`) values ('Евгений Онегин');
insert into book(`name`) values ('1984');
insert into book(`name`) values ('Мы');
insert into book(`name`) values ('Триумфальная арка');
insert into book(`name`) values ('Трое в лодке, не считая собаки');
insert into book(`name`) values ('Тихий Дон');
insert into book(`name`) values ('Семь смертей Эвелины Хардкасал');
insert into book(`name`) values ('Снеговик');
insert into book(`name`) values ('Почти идеальные люди');
insert into book(`name`) values ('Краткая история времени');
insert into book(`name`) values ('Унесенные ветром');

--Комментарии
insert into comment(book_id, `text`) values ((select id from book where name = 'Сильмариллион'),'Комментарий 1 к книге Сильмариллион');
insert into comment(book_id, `text`) values ((select id from book where name = 'Сильмариллион'),'Комментарий 2 к книге Сильмариллион');


--Связь книг с авторами
insert into book_author_link(book_id, author_id) values ((select id from book where name = 'Сильмариллион'), (select id from author where name = 'Джон Р.Р. Толкин'));
insert into book_author_link(book_id, author_id) values ((select id from book where name = 'Властелин Колец'), (select id from author where name = 'Джон Р.Р. Толкин'));
insert into book_author_link(book_id, author_id) values ((select id from book where name = 'Гарри Поттер и Философский камень'), (select id from author where name = 'Дж. К. Роулинг'));
insert into book_author_link(book_id, author_id) values ((select id from book where name = 'Sapiens. Краткая история человечества'), (select id from author where name = 'Ю.Н. Харари'));
insert into book_author_link(book_id, author_id) values ((select id from book where name = 'Убийство в «Восточном экспрессе»'), (select id from author where name = 'Агата Кристи'));
insert into book_author_link(book_id, author_id) values ((select id from book where name = 'Горе от ума'), (select id from author where name = 'Грибоедов А.С.'));
insert into book_author_link(book_id, author_id) values ((select id from book where name = 'Война и мир'), (select id from author where name = 'Толстой Л.Н.'));
insert into book_author_link(book_id, author_id) values ((select id from book where name = 'Над пропостью во ржи'), (select id from author where name = 'Д. Сэлинджер'));
insert into book_author_link(book_id, author_id) values ((select id from book where name = 'Мифы Древней Греции'), (select id from author where name = 'Н.Кун'));
insert into book_author_link(book_id, author_id) values ((select id from book where name = 'Евгений Онегин'), (select id from author where name = 'Пушкин А.С.'));
insert into book_author_link(book_id, author_id) values ((select id from book where name = '1984'), (select id from author where name = 'Д. Оруэлл'));
insert into book_author_link(book_id, author_id) values ((select id from book where name = 'Мы'), (select id from author where name = 'Замятин Е.Н.'));
insert into book_author_link(book_id, author_id) values ((select id from book where name = 'Триумфальная арка'), (select id from author where name = 'Эрих Мария Ремарк'));
insert into book_author_link(book_id, author_id) values ((select id from book where name = 'Трое в лодке, не считая собаки'), (select id from author where name = 'Дж. К. Джером'));
insert into book_author_link(book_id, author_id) values ((select id from book where name = 'Тихий Дон'), (select id from author where name = 'Шолохов М.А.'));
insert into book_author_link(book_id, author_id) values ((select id from book where name = 'Семь смертей Эвелины Хардкасал'), (select id from author where name = 'С. Тёртон'));
insert into book_author_link(book_id, author_id) values ((select id from book where name = 'Снеговик'), (select id from author where name = 'Ю. Нёсбе'));
insert into book_author_link(book_id, author_id) values ((select id from book where name = 'Почти идеальные люди'), (select id from author where name = 'М. Бут'));
insert into book_author_link(book_id, author_id) values ((select id from book where name = 'Краткая история времени'), (select id from author where name = 'С. Хокинг'));
insert into book_author_link(book_id, author_id) values ((select id from book where name = 'Унесенные ветром'), (select id from author where name = 'М. Митчелл'));

--Связь книг с жанрами
insert into book_genre_link(book_id, genre_id) values ((select id from book where name = 'Сильмариллион'), (select id from genre where name = 'Фэнтези'));
insert into book_genre_link(book_id, genre_id) values ((select id from book where name = 'Властелин Колец'), (select id from genre where name = 'Фэнтези'));
insert into book_genre_link(book_id, genre_id) values ((select id from book where name = 'Гарри Поттер и Философский камень'), (select id from genre where name = 'Фэнтези'));
insert into book_genre_link(book_id, genre_id) values ((select id from book where name = 'Sapiens. Краткая история человечества'), (select id from genre where name = 'Нон-фикшн'));
insert into book_genre_link(book_id, genre_id) values ((select id from book where name = 'Убийство в «Восточном экспрессе»'), (select id from genre where name = 'Детектив'));
insert into book_genre_link(book_id, genre_id) values ((select id from book where name = 'Горе от ума'), (select id from genre where name = 'Комедия'));
insert into book_genre_link(book_id, genre_id) values ((select id from book where name = 'Война и мир'), (select id from genre where name = 'Любовный роман'));
insert into book_genre_link(book_id, genre_id) values ((select id from book where name = 'Война и мир'), (select id from genre where name = 'Исторический роман'));
insert into book_genre_link(book_id, genre_id) values ((select id from book where name = 'Война и мир'), (select id from genre where name = 'Военная проза'));
insert into book_genre_link(book_id, genre_id) values ((select id from book where name = 'Над пропостью во ржи'), (select id from genre where name = 'Роман воспитания'));
insert into book_genre_link(book_id, genre_id) values ((select id from book where name = 'Мифы Древней Греции'), (select id from genre where name = 'Мифы и легенды'));
insert into book_genre_link(book_id, genre_id) values ((select id from book where name = 'Евгений Онегин'), (select id from genre where name = 'Роман в стихах'));
insert into book_genre_link(book_id, genre_id) values ((select id from book where name = '1984'), (select id from genre where name = 'Научная фантастика'));
insert into book_genre_link(book_id, genre_id) values ((select id from book where name = 'Мы'), (select id from genre where name = 'Антиутопия'));
insert into book_genre_link(book_id, genre_id) values ((select id from book where name = 'Мы'), (select id from genre where name = 'Любовный роман'));
insert into book_genre_link(book_id, genre_id) values ((select id from book where name = 'Мы'), (select id from genre where name = 'Научная фантастика'));
insert into book_genre_link(book_id, genre_id) values ((select id from book where name = 'Триумфальная арка'), (select id from genre where name = 'Военная проза'));
insert into book_genre_link(book_id, genre_id) values ((select id from book where name = 'Триумфальная арка'), (select id from genre where name = 'Исторический роман'));
insert into book_genre_link(book_id, genre_id) values ((select id from book where name = 'Трое в лодке, не считая собаки'), (select id from genre where name = 'Юмористическая повесть'));
insert into book_genre_link(book_id, genre_id) values ((select id from book where name = 'Тихий Дон'), (select id from genre where name = 'Исторический роман'));
insert into book_genre_link(book_id, genre_id) values ((select id from book where name = 'Семь смертей Эвелины Хардкасал'), (select id from genre where name = 'Триллер'));
insert into book_genre_link(book_id, genre_id) values ((select id from book where name = 'Снеговик'), (select id from genre where name = 'Детектив'));
insert into book_genre_link(book_id, genre_id) values ((select id from book where name = 'Снеговик'), (select id from genre where name = 'Триллер'));
insert into book_genre_link(book_id, genre_id) values ((select id from book where name = 'Почти идеальные люди'), (select id from genre where name = 'Страноведение'));
insert into book_genre_link(book_id, genre_id) values ((select id from book where name = 'Краткая история времени'), (select id from genre where name = 'Научпоп'));
insert into book_genre_link(book_id, genre_id) values ((select id from book where name = 'Унесенные ветром'), (select id from genre where name = 'Исторический роман'));
