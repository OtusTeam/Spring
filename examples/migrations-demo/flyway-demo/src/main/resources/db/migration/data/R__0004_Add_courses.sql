insert into courses (id, name)
values (1, 'Разработчик Java'),
       (2, 'Разработчик на Spring Framework'),
       (3, 'Fullstack разработчик JavaScript'),
       (4, 'Team Lead 2.0');

insert into users_courses (user_id, course_id)
values (1, 1), (1, 2), (1, 3), (1, 4),
       (2, 1), (2, 2), (1, 3),
       (3, 3), (3, 4);