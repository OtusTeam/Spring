insert into avatars(photo_url)
values ('photoUrl_01'), ('photoUrl_02'), ('photoUrl_03'), ('photoUrl_04'), ('photoUrl_05'),
       ('photoUrl_06'), ('photoUrl_07'), ('photoUrl_08'), ('photoUrl_09'), ('photoUrl_10');

insert into courses(name)
values ('course_name_01'), ('course_name_02'), ('course_name_03'), ('course_name_04'), ('course_name_05'),
       ('course_name_06'), ('course_name_07'), ('course_name_08'), ('course_name_09'), ('course_name_10'), ('not_used_11');

insert into otus_students(name, avatar_id)
values ('student_01', 1), ('student_02', 2), ('student_03', 3), ('student_04', 4), ('student_05', 5),
       ('student_06', 6), ('student_07', 7), ('student_08', 8), ('student_09', 9), ('student_10', 10);


insert into emails(email, student_id)
values ('email_01', 1), ('email_02', 1), ('email_03', 2), ('email_04', 2), ('email_05', 3), ('email_06', 4),
       ('email_07', 5), ('email_08', 6), ('email_09', 7), ('email_10', 8), ('email_11', 9), ('email_12', 10);


insert into student_courses(student_id, course_id)
values (1, 1),   (1, 2),   (1, 3),
       (2, 2),   (2, 4),   (2, 5),
       (3, 3),   (3, 6),   (3, 7),
       (4, 4),   (4, 8),   (4, 9),
       (5, 5),   (5, 10),  (5, 1),
       (6, 6),   (6, 2),   (6, 3),
       (7, 7),   (7, 4),   (7, 5),
       (8, 8),   (8, 6),   (8, 7),
       (9, 9),   (9, 8),   (9, 10),
       (10, 10), (10, 1),  (10, 2);
