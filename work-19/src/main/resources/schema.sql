drop table if exists books;
create table books(
id bigint primary key auto_increment,
  name        varchar(255),
  description varchar(1000),
  author_id   bigint,
  genre_id    bigint);

drop table if exists authors;
create table authors(
id bigint primary key auto_increment,
  name        varchar(255),
  birth_yar   date,
  description varchar(1000));

drop table if exists genres;
create table genres(
id bigint primary key auto_increment,
  name varchar(255));

drop table if exists comments;
create table comments(
id bigint primary key auto_increment,
    book_id bigint references books(id) on delete cascade,
    text varchar(4000));