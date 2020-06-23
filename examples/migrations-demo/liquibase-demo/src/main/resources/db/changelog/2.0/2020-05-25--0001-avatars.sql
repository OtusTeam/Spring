--liquibase formatted sql

--changeset stvort:2020-05-25-001-avatars
create table avatars (
    id bigserial,
    photo_url varchar(255),
    primary key (id)
)

--changeset stvort:2020-05-25-001-users-avatars-id
alter table users
add column avatar_id bigint references avatars(id) ON DELETE SET NULL