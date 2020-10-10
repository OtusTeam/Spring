--date: 2002-10-10
--author: stvort

create table authors (id bigserial, name varchar(50), primary key (id));
create table genres (id bigserial, name varchar(50), primary key (id));
create table books (id bigserial, title varchar(50), book_text varchar(8000), primary key (id));
create table books_authors (book_id bigint not null references books (id) on delete cascade , author_id bigint not null references authors (id) on delete cascade);
create table books_genres (book_id bigint not null references books (id) on delete cascade , genre_id bigint not null references genres (id) on delete cascade);


create view books_all_info as
select b.id as "ID книги",
       b.title as "Заголовок книги",
       b.book_text as "Текст книги",
       a.id as "ID автора",
       a.name as "Имя автора",
       g.id as "ID жанра",
       g.name as "Название жанра"
from books b left join
     books_authors ba on b.id = ba.book_id left join
     books_genres bg on b.id = bg.book_id left join
     authors a on ba.author_id = a.id left join
     genres g on bg.genre_id = g.id;
