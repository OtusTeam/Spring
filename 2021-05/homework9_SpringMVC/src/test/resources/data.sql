DELETE FROM TRAINING_RESULTS;
DELETE FROM TRAININGS;
DELETE FROM WORDS;
DELETE FROM DICTIONARIES;
DELETE FROM USERS;

insert into users (id, name) values (1, 'admin');

insert into dictionaries (id, name, create_date, user_id, description)
values (1, 'main dict', '2021-12-09', 1, 'my main dictionary');

insert into words (id, name, translation, context, example,
                   add_date, learnt_date, state,
                   learnt_percent, dictionary_id)
values (1, 'tell off', 'отчитывать', 'cambridge dictionary',
        'Their teacher told them off for chattering in the lesson.',
        '2021-12-10', null, 1, 0, 1);