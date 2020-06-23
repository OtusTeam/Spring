--liquibase formatted sql

--changeset stvort:2020-05-25-002-courses
insert into courses (id, name)
values (1, 'Разработчик Java'),
       (2, 'Разработчик на Spring Framework'),
       (3, 'Fullstack разработчик JavaScript'),
       (4, 'Team Lead 2.0')

--changeset stvort:2020-05-25-002-users-courses
insert into users_courses (user_id, course_id)
values (1, 1), (1, 2), (1, 3), (1, 4),
       (2, 1), (2, 2), (2, 3),
       (3, 3), (3, 4)