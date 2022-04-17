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

DELETE
FROM ACL_ENTRY;
DELETE
FROM ACL_OBJECT_IDENTITY;
DELETE
FROM ACL_CLASS;
DELETE
FROM ACL_SID;

ALTER SEQUENCE WORD_ID_SEQ RESTART WITH 1;
ALTER SEQUENCE DICT_ID_SEQ RESTART WITH 1;
ALTER SEQUENCE TRAIN_ID_SEQ RESTART WITH 1;
ALTER SEQUENCE USER_ID_SEQ RESTART WITH 1;

insert into USERS (ID, NAME, PASSWORD, ROLE)
values (1, 'lenu', 'password', 'ROLE_USER'),
       (2, 'admin', 'admin', 'ROLE_ADMIN'),
       (3, 'user1', 'user1', 'ROLE_USER');

insert into DICTIONARIES (id, NAME, CREATE_DATE, DESCRIPTION)
values (1, 'My Dictionary', '2021-12-09', 'main dictionary'),
       (2, 'Animals', '2021-12-09', 'dictionary about animals'),
       (3, 'Other user dictionary', '2021-12-09', 'someone^s else dictionary');
;


insert into WORDS (NAME, TRANSLATION, CONTEXT, EXAMPLE, ADD_DATE, LEARNT_DATE, LEARNT_PERCENT, STATE, DICTIONARY_ID)
values ('tenacious', 'упорный, упрямый', 'merriam-webster',
        'The tenacious king salmon will likely be coming back for many, many autumns to come.',
        '2021-12-09', null, 0, 1, 1),
       ('treadmill', 'беговая дорожка', 'Dr. House',
        'Put the patient on a treadmill.',
        '2021-12-09', null, 0, 1, 1);

INSERT INTO acl_sid (id, principal, sid) VALUES
(1, 1, 'lenu'),
(2, 1, 'admin'),
(3, 1, 'user1'),
(4, 0, 'ROLE_USER'),
(5, 0, 'ROLE_ADMIN');


INSERT INTO acl_class (id, class) VALUES
(1, 'com.example.homework13_acl.model.Dictionary');

INSERT INTO acl_object_identity (id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting) VALUES
(1, 1, 1, NULL, 1, 0),
(2, 1, 2, NULL, 1, 0),
(3, 1, 3, NULL, 3, 0);

INSERT INTO acl_entry (id, acl_object_identity, ace_order, sid, mask,
                       granting, audit_success, audit_failure) VALUES
(1, 1, 1, 1, 1, 1, 1, 1),
(2, 2, 2, 1, 1, 1, 1, 1),
(3, 3, 3, 3, 1, 1, 1, 1);
-- (4, 1, 4, 4, 1, 1, 1, 1),
-- (5, 2, 5, 4, 1, 1, 1, 1),
-- (6, 3, 6, 4, 1, 1, 1, 1);