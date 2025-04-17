DROP TABLE IF EXISTS persons;

CREATE TABLE persons (
    id BIGSERIAL,
    name VARCHAR(250),

    PRIMARY KEY (id)
);