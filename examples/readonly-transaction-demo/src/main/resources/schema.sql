drop table if exists person;

create table person  (
    id bigserial,
    name varchar(200),
    primary key(id)
);
