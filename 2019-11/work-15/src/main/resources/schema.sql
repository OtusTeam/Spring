drop table IF EXISTS BOOKS;
create TABLE BOOKS(
ID BIGINT PRIMARY KEY auto_increment,
  NAME        VARCHAR(255),
  DESCRIPTION VARCHAR(1000),
  AUTHOR_ID   BIGINT,
  GENRE_ID    BIGINT);

drop table IF EXISTS AUTHORS;
create TABLE AUTHORS(
ID BIGINT PRIMARY KEY auto_increment,
  NAME        VARCHAR(255),
  BIRTH_YAR   DATE,
  DESCRIPTION VARCHAR(1000));

drop table IF EXISTS GENRES;
create TABLE GENRES(
ID BIGINT PRIMARY KEY auto_increment,
  NAME VARCHAR(255));

drop table IF EXISTS COMMENTS;
create table comments(
ID BIGINT PRIMARY KEY auto_increment,
    BOOK_ID BIGINT references BOOKS(id) on delete cascade,
    text VARCHAR(4000));

drop table IF EXISTS USERS;
create TABLE USERS(
ID BIGINT PRIMARY KEY auto_increment,
  USERNAME VARCHAR(255),
  PASSWORD VARCHAR(100),
  NONEXPIRED TINYINT NOT NULL,
  NONLOCKED TINYINT NOT NULL,
  CREDENTIALSNONEXPIRED TINYINT NOT NULL,
  ENABLED TINYINT NOT NULL DEFAULT 1,
  UNIQUE KEY UK_USERNAME (USERNAME)
  );

drop table IF EXISTS USER_AUTHORITIES;
CREATE TABLE USER_AUTHORITIES (
ID BIGINT PRIMARY KEY auto_increment,
    USERNAME varchar(255) NOT NULL,
    AUTHORITY varchar(45) NOT NULL,
    UNIQUE KEY UK_USERNAMEROLE (USERNAME,AUTHORITY)
    );

-- acl tables
CREATE TABLE IF NOT EXISTS acl_sid (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  principal tinyint(1) NOT NULL,
  sid varchar(100) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY unique_uk_1 (sid,principal)
);

CREATE TABLE IF NOT EXISTS acl_class (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  class varchar(255) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY unique_uk_2 (class)
);

CREATE TABLE IF NOT EXISTS acl_entry (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  acl_object_identity bigint(20) NOT NULL,
  ace_order int(11) NOT NULL,
  sid bigint(20) NOT NULL,
  mask int(11) NOT NULL,
  granting tinyint(1) NOT NULL,
  audit_success tinyint(1) NOT NULL,
  audit_failure tinyint(1) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY unique_uk_4 (acl_object_identity,ace_order)
);

CREATE TABLE IF NOT EXISTS acl_object_identity (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  object_id_class bigint(20) NOT NULL,
  object_id_identity bigint(20) NOT NULL,
  parent_object bigint(20) DEFAULT NULL,
  owner_sid bigint(20) DEFAULT NULL,
  entries_inheriting tinyint(1) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY unique_uk_3 (object_id_class,object_id_identity)
);

ALTER TABLE acl_entry
ADD FOREIGN KEY (acl_object_identity) REFERENCES acl_object_identity(id);

ALTER TABLE acl_entry
ADD FOREIGN KEY (sid) REFERENCES acl_sid(id);

ALTER TABLE acl_object_identity
ADD FOREIGN KEY (parent_object) REFERENCES acl_object_identity (id);

ALTER TABLE acl_object_identity
ADD FOREIGN KEY (object_id_class) REFERENCES acl_class (id);

ALTER TABLE acl_object_identity
ADD FOREIGN KEY (owner_sid) REFERENCES acl_sid (id);