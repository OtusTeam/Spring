create table if not exists person
(
    id         bigint primary key auto_increment,
    last_name  varchar(50)  not null,
    age        int not null
);

create table if not exists notes
(
    id           bigint primary key auto_increment,
    note_text    varchar(250) not null,
    person_id    bigint not null references person (id)
);
create index if not exists idx_notes_person_id on notes (person_id);