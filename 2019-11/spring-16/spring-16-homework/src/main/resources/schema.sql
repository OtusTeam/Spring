create table authors(
    id bigserial,
    name varchar(1000) not null,
    primary key (id)
);

create table genres(
    id bigserial,
    name varchar(255) not null,
    primary key (id)
);

create table books(
    id bigserial,
    caption varchar(1000) not null,
    primary key (id)
);

create table books_authors(
    book_id bigint references books(id) on delete cascade,
    author_id bigint references authors(id) on delete cascade,
    primary key (book_id, author_id)
);

create table books_genres(
    book_id bigint references books(id) on delete cascade,
    genre_id bigint references genres(id) on delete cascade,
    primary key (book_id, genre_id)
);

create table comments(
    id bigserial,
    book_id bigint references books(id) on delete cascade,
    comment varchar(1000) not null,
    primary key (id)
);
