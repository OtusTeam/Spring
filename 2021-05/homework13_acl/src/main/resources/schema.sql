DROP TABLE IF EXISTS words CASCADE;
DROP TABLE IF EXISTS dictionaries;
DROP TABLE IF EXISTS trainings_results;
DROP TABLE IF EXISTS trainings;
DROP TABLE IF EXISTS users;

DROP TABLE IF EXISTS acl_entry;
DROP TABLE IF EXISTS acl_object_identity;

DROP TABLE IF EXISTS acl_class;
DROP TABLE IF EXISTS acl_sid;


DROP SEQUENCE IF EXISTS word_id_seq;
DROP SEQUENCE IF EXISTS dict_id_seq;
DROP SEQUENCE IF EXISTS user_id_seq;
DROP SEQUENCE IF EXISTS train_id_seq;
DROP SEQUENCE IF EXISTS result_id_seq;

CREATE SEQUENCE word_id_seq START WITH 1;
CREATE SEQUENCE dict_id_seq START WITH 1;
CREATE SEQUENCE user_id_seq START WITH 1;
CREATE SEQUENCE train_id_seq START WITH 1;
CREATE SEQUENCE result_id_seq START WITH 1;

CREATE TABLE users
(
    id       BIGSERIAL,
    name     VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role     VARCHAR(255) NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);

CREATE TABLE dictionaries
(
    id          BIGINT NOT NULL,
    name        VARCHAR(255) NOT NULL,
    create_date DATE         NOT NULL,
    description VARCHAR(2048),
--     user_id     BIGINT REFERENCES users,
    CONSTRAINT dictionaries_pkey PRIMARY KEY (id)
);

CREATE TABLE words
(
    id             BIGSERIAL,
    name           VARCHAR(255) NOT NULL,
    translation    VARCHAR(255) NOT NULL,
    context        VARCHAR(255),
    example        VARCHAR(2048),
    add_date       DATE,
    learnt_date    DATE,
    learnt_percent INTEGER,
    state          VARCHAR(255),
    dictionary_id  BIGINT REFERENCES dictionaries,
    CONSTRAINT words_pkey PRIMARY KEY (id)
);

create table trainings
(
    id         bigint,
    train_date datetime,
    user_id    BIGINT REFERENCES users,
    CONSTRAINT trainings_pkey PRIMARY KEY (id)
);

create table training_results
(
    id          bigint,
    word_id     bigint references words (id) on delete cascade,
    training_id bigint references trainings (id),
    success     boolean,
    CONSTRAINT results_pkey PRIMARY KEY (id)
);

CREATE TABLE acl_sid
(
    id        bigint  NOT NULL AUTO_INCREMENT,
    principal integer  NOT NULL,
    sid       varchar(100) NOT NULL,
    CONSTRAINT sid_pkey PRIMARY KEY (id),
    CONSTRAINT unique_uk_1 UNIQUE (sid,principal)
);

CREATE TABLE acl_class
(
    id    bigint   NOT NULL AUTO_INCREMENT,
    class varchar(255) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT unique_uk_2 UNIQUE(class)
);

CREATE TABLE IF NOT EXISTS acl_entry
(
    id                  bigint NOT NULL AUTO_INCREMENT,
    acl_object_identity bigint NOT NULL,
    ace_order           integer    NOT NULL,
    sid                 bigint NOT NULL,
    mask                integer    NOT NULL,
    granting            tinyint NOT NULL,
    audit_success       tinyint NOT NULL,
    audit_failure       tinyint NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT unique_uk_4 UNIQUE (acl_object_identity,ace_order)
);

CREATE TABLE IF NOT EXISTS acl_object_identity
(
    id                 bigint NOT NULL AUTO_INCREMENT,
    object_id_class    bigint NOT NULL,
    object_id_identity bigint NOT NULL,
    parent_object      bigint DEFAULT NULL,
    owner_sid          bigint DEFAULT NULL,
    entries_inheriting tinyint NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT unique_uk_3 UNIQUE (object_id_class,object_id_identity)
);

ALTER TABLE acl_entry
    ADD FOREIGN KEY (acl_object_identity) REFERENCES acl_object_identity (id);

ALTER TABLE acl_entry
    ADD FOREIGN KEY (sid) REFERENCES acl_sid (id);

--
-- Constraints for table acl_object_identity
--
ALTER TABLE acl_object_identity
    ADD FOREIGN KEY (parent_object) REFERENCES acl_object_identity (id);

ALTER TABLE acl_object_identity
    ADD FOREIGN KEY (object_id_class) REFERENCES acl_class (id);

ALTER TABLE acl_object_identity
    ADD FOREIGN KEY (owner_sid) REFERENCES acl_sid (id);

