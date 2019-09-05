INSERT INTO knowledge (name) VALUES ('Spring Core'), ('Spring Data'), ('Spring MVC'), ('Spring Batch'), ('Spring Integration'), ('Spring WebFlux');

INSERT INTO students (name) VALUES ('Student #01'), ('Student #02'), ('Student #03'), ('Student #04'), ('Student #05'), ('Student #06'), ('Student #07'), ('Student #08'), ('Student #09');
INSERT INTO mentors (name) VALUES ('Mentor #01'), ('Mentor #02'), ('Mentor #03'), ('Mentor #04'), ('Mentor #05'), ('Mentor #06'), ('Mentor #07'), ('Mentor #08'), ('Mentor #09');
INSERT INTO teachers (name) VALUES ('Teacher #01'), ('Teacher #02'), ('Teacher #03'), ('Teacher #04'), ('Teacher #05'), ('Teacher #06'), ('Teacher #07'), ('Teacher #08'), ('Teacher #09');

INSERT INTO students_experience (student_id, knowledge_id) VALUES (1, 1), (1, 2), (2, 2), (2, 3), (2, 4), (2, 5);
INSERT INTO mentors_experience  (mentor_id, knowledge_id)  VALUES (1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (2, 1), (2, 2), (2, 3), (2, 4), (2, 5), (2, 6);
INSERT INTO teachers_experience (teacher_id, knowledge_id) VALUES(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (2, 1), (2, 2), (2, 3), (2, 4), (2, 5), (2, 6);