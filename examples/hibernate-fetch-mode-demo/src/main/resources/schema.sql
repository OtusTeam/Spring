DROP TABLE IF EXISTS students_experience;
DROP TABLE IF EXISTS mentors_experience;
DROP TABLE IF EXISTS teachers_experience;
DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS mentors;
DROP TABLE IF EXISTS teachers;
DROP TABLE IF EXISTS knowledge;

CREATE TABLE knowledge (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE students (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE mentors (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE teachers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE students_experience (
    student_id BIGINT,
    knowledge_id BIGINT,
    FOREIGN KEY(student_id) REFERENCES students(id) ON DELETE CASCADE,
    FOREIGN KEY(knowledge_id) REFERENCES knowledge(id) ON DELETE CASCADE
);

CREATE TABLE mentors_experience (
    mentor_id BIGINT,
    knowledge_id BIGINT,
    FOREIGN KEY(mentor_id) REFERENCES mentors(id) ON DELETE CASCADE,
    FOREIGN KEY(knowledge_id) REFERENCES knowledge(id) ON DELETE CASCADE
);

CREATE TABLE teachers_experience (
    teacher_id BIGINT,
    knowledge_id BIGINT,
    FOREIGN KEY(teacher_id) REFERENCES teachers(id) ON DELETE CASCADE,
    FOREIGN KEY(knowledge_id) REFERENCES knowledge(id) ON DELETE CASCADE
);