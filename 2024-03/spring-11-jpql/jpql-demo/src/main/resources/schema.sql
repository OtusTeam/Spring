DROP TABLE IF EXISTS employees_projects;
DROP TABLE IF EXISTS addresses;
DROP TABLE IF EXISTS departments;
DROP TABLE IF EXISTS projects;
DROP TABLE IF EXISTS employees;

CREATE TABLE addresses (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    city VARCHAR(255)
);

CREATE TABLE departments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE projects (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);


CREATE TABLE employees (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    salary BIGINT,
    address_id BIGINT,
    department_id BIGINT,
    FOREIGN KEY(address_id) REFERENCES addresses(id) ON DELETE CASCADE,
    FOREIGN KEY(department_id) REFERENCES departments(id) ON DELETE CASCADE
);

CREATE TABLE employees_projects (
    employee_id BIGINT,
    project_id BIGINT,
    FOREIGN KEY(employee_id) REFERENCES employees(id) ON DELETE CASCADE,
    FOREIGN KEY(project_id) REFERENCES projects(id) ON DELETE CASCADE
);


CREATE TABLE categories (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    parent_category_id BIGINT REFERENCES categories(id) ON DELETE CASCADE,
    name VARCHAR(255)
);