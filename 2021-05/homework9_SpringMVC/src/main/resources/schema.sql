DROP TABLE IF EXISTS words CASCADE;
DROP TABLE IF EXISTS dictionaries;
DROP TABLE IF EXISTS trainings_results;
DROP TABLE IF EXISTS trainings;
DROP TABLE IF EXISTS users;


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
    id   BIGINT,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);

CREATE TABLE dictionaries
(
    id          BIGINT,
    name        VARCHAR(255) NOT NULL,
    create_date DATE         NOT NULL,
    description VARCHAR(2048),
    user_id     BIGINT REFERENCES users,
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

