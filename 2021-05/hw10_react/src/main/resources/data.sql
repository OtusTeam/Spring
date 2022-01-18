DELETE
FROM TRAINING_RESULTS;
DELETE
FROM TRAININGS;
DELETE
FROM WORDS;
DELETE
FROM DICTIONARIES;
DELETE
FROM USERS;

ALTER SEQUENCE WORD_ID_SEQ RESTART WITH 1;
ALTER SEQUENCE DICT_ID_SEQ RESTART WITH 1;
ALTER SEQUENCE TRAIN_ID_SEQ RESTART WITH 1;
ALTER SEQUENCE USER_ID_SEQ RESTART WITH 1;

insert into USERS (ID, NAME)
values (1, 'lenu');

insert into DICTIONARIES (ID, NAME, CREATE_DATE, DESCRIPTION, USER_ID)
values (1, 'My Dictionary', '2021-12-09', 'main dictionary', 1);

insert into WORDS (NAME, TRANSLATION, CONTEXT, EXAMPLE, ADD_DATE, LEARNT_DATE, LEARNT_PERCENT, STATE, DICTIONARY_ID)
values ('tenacious', 'упорный, упрямый', 'merriam-webster',
        'The tenacious king salmon will likely be coming back for many, many autumns to come.',
        '2021-12-09', null, 0, 1, 1),
       ('treadmill', 'беговая дорожка', 'Dr. House',
        'Put the patient on a treadmill.',
        '2021-12-09', null, 0, 1, 1);
