INSERT INTO addresses (city) VALUES ('Saratov'), ('Omsk'), ('Moscow');
INSERT INTO departments (name) VALUES ('IT'), ('AHO');
INSERT INTO projects (name) VALUES ('Project #1'), ('Project #2'), ('Project #3'), ('Project #4');

INSERT INTO employees (first_name, last_name, salary, address_id, department_id)
VALUES ('fn1', 'ln1', 70000, 1, 1),
       ('fn2', 'ln2', 99998, 1, null),
       ('fn3', 'ln3', 30000, 1, 2),

       ('fn4', 'ln4', 170000, 2, 1),

       ('fn5', 'ln5', 120000, 3, 1),
       ('fn6', 'ln6', 100400, 3, 1),
       ('fn7', 'ln7', 100000, 3, 1),
       ('fn8', 'ln8', 1000000, 3, null);


INSERT INTO employees_projects (employee_id, project_id)
VALUES (1, 1), (1, 2), (1, 3),
       (2, 3), (2, 4),
       (4, 1), (4, 2), (4, 3), (4, 4);


INSERT INTO categories (parent_category_id, name)
VALUES (null, 'Parent category #1'), (null, 'Parent category #2'), (null, 'Parent category #3'),
       (1, 'Child category #1'), (2, 'Child category #2'), (3, 'Child category #3');