--liquibase formatted sql

--changeset stvort:2020-05-25-002-avatars
create table avatars (
    id bigserial,
    photo_url varchar(255)
)

--changeset stvort:2020-05-25-002-users-avatars-id
alter table users
add column avatar_id bigint references avatars(id) ON DELETE SET NULL