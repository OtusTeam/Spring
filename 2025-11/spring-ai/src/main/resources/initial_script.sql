CREATE EXTENSION IF NOT EXISTS vector;

CREATE TABLE IF NOT EXISTS tasks (
    id BIGSERIAL PRIMARY KEY,
    requirement TEXT NOT NULL,
    user_story VARCHAR(500) NOT NULL,
    acceptance_criteria TEXT NOT NULL,
    complexity INTEGER NOT NULL,
    embedding vector(768) NOT NULL
);