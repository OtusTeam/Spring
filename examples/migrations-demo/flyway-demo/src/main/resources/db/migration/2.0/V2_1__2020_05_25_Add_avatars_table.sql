--date: 2020-05-25
--author: stvort

create table avatars (
    id bigserial,
    photo_url varchar(255)
);

alter table users
add column avatar_id bigint references avatars(id) ON DELETE SET NULL;