create table person
(
    id         bigserial    not null primary key,
    last_name  varchar(50)  not null,
    age        int not null
);

create table notes
(
    id           bigserial   not null primary key,
    note_text    varchar(250) not null,
    person_id    bigint not null references person (id)
);
create index idx_notes_person_id on notes (person_id);

