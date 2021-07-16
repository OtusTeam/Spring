drop table if exists person;
drop table if exists email;


create table email  (
    id bigserial,
    address varchar(200),
    primary key(id)
);

create table person  (
    id bigserial,
    email_id bigint references email(id),
    name varchar(200),
    primary key(id)
);

