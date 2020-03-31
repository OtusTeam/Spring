insert into AUTHORS (ID, NAME, BIRTH_YAR, DESCRIPTION) values (1, 'Harry harrison', '1925-03-12','Harry Harrison is the pseudonym of Henry Maxwell Dempsey, a famous American science fiction writer and editor. Born March 12, 1925 in Stamford');

insert into GENRES (ID, NAME) values(1, 'Fantasy');

insert into BOOKS (ID, NAME, DESCRIPTION, AUTHOR_ID, GENRE_ID) values (1, 'The Birth of Steel Rat','The magnificent Jim Dee Gris - a famous interstellar criminal - received for his ingenuity and determination a well-known nickname Stainless Steel Rat', 1, 1);
insert into BOOKS (ID, NAME, DESCRIPTION, AUTHOR_ID, GENRE_ID) values (2, 'Bill, the Galactic Hero: On the Planet of Tasteless Pleasure','1991', 1, 1);
insert into BOOKS (ID, NAME, DESCRIPTION, AUTHOR_ID, GENRE_ID) values (3, 'Planet of the Damned (Sense of Obligation)','1962', 1, 1);
insert into BOOKS (ID, NAME, DESCRIPTION, AUTHOR_ID, GENRE_ID) values (4, 'Planet of No Return','1981', 1, 1);
insert into BOOKS (ID, NAME, DESCRIPTION, AUTHOR_ID, GENRE_ID) values (5, 'Stars and Stripes Forever','1998', 1, 1);

insert into COMMENTS (ID, BOOK_ID, TEXT) values(1, 1, 'Text');

--- users
insert into USERS (ID, USERNAME, PASSWORD, NONEXPIRED, NONLOCKED, CREDENTIALSNONEXPIRED, ENABLED) values(1, 'admin', '123', 1, 1, 1, 1);
insert into USERS (ID, USERNAME, PASSWORD, NONEXPIRED, NONLOCKED, CREDENTIALSNONEXPIRED, ENABLED) values(2, 'user', '111', 1, 1, 1, 1);

insert into USER_AUTHORITIES (ID, USERNAME, AUTHORITY) values(1, 'admin', 'ROLE_ADMIN');
insert into USER_AUTHORITIES (ID, USERNAME, AUTHORITY) values(2, 'user', 'ROLE_USER');

--- acl
insert into acl_sid (id, principal, sid) values(1, 1, 'admin');
insert into acl_sid (id, principal, sid) values(2, 1, 'user');
insert into acl_sid (id, principal, sid) values(3, 0, 'ROLE_ADMIN');

insert into acl_class (id, class) values(1, 'ru.otus.work.domain.Book');

insert into acl_object_identity (id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting) values
(1, 1, 1, NULL, 1, 0),
(2, 1, 2, NULL, 1, 0),
(3, 1, 3, NULL, 1, 0),
(4, 1, 4, NULL, 2, 0),
(5, 1, 5, NULL, 2, 0);

insert into acl_entry (id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) values
(1, 1, 1, 1, 1, 1, 1, 1),
(2, 1, 2, 1, 2, 1, 1, 1),
(3, 2, 1, 1, 1, 1, 1, 1),
(4, 2, 2, 1, 2, 1, 1, 1),
(5, 3, 1, 1, 1, 1, 1, 1),
(6, 3, 2, 1, 2, 1, 1, 1),
(7, 4, 1, 2, 1, 1, 1, 1),
(8, 5, 1, 2, 1, 1, 1, 1);