select user from dual;

SELECT table_name
FROM user_tables;


CREATE TABLE student (
    id VARCHAR2(10)
        CONSTRAINT student_pk PRIMARY KEY,
    first_name VARCHAR2(50),
    last_name VARCHAR2(50)
);

CREATE TABLE enrollment (
    id NUMBER
        CONSTRAINT enrollment_pk PRIMARY KEY,
    student_id VARCHAR2(10),
    course VARCHAR2(50),

    CONSTRAINT enrollment_student_fk
        FOREIGN KEY (student_id)
        REFERENCES student(id)
);

INSERT INTO student
(id, first_name, last_name)
VALUES
('S001', 'Sean', 'Luzano');

INSERT INTO student
(id, first_name, last_name)
VALUES
('S002', 'Maria', 'Santos');

INSERT INTO student
(id, first_name, last_name)
VALUES
('S003', 'Juan', 'Dela Cruz');

INSERT INTO enrollment
(id, student_id, course)
VALUES
(1, 'S001', 'Database');

INSERT INTO enrollment
(id, student_id, course)
VALUES
(2, 'S002', 'Java');

commit;

select * from student;
select * from enrollment;

SELECT
    s.id,
    s.first_name,
    s.last_name,
    e.course
FROM student s
JOIN enrollment e
    ON s.id = e.student_id
ORDER BY s.id;
