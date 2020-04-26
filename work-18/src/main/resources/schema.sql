drop table IF EXISTS BOOKS;
create TABLE BOOKS(
ID BIGINT PRIMARY KEY auto_increment,
  NAME        VARCHAR(255),
  DESCRIPTION VARCHAR(1000),
  AUTHOR_ID   BIGINT,
  GENRE_ID    BIGINT);

drop table IF EXISTS AUTHORS;
create TABLE AUTHORS(
ID BIGINT PRIMARY KEY auto_increment,
  NAME        VARCHAR(255),
  BIRTH_YAR   DATE,
  DESCRIPTION VARCHAR(1000));

drop table IF EXISTS GENRES;
create TABLE GENRES(
ID BIGINT PRIMARY KEY auto_increment,
  NAME VARCHAR(255));

drop table IF EXISTS COMMENTS;
create table comments(
ID BIGINT PRIMARY KEY auto_increment,
    BOOK_ID BIGINT references BOOKS(id) on delete cascade,
    text VARCHAR(4000));