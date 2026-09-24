ALTER SESSION SET CONTAINER = CDB$ROOT;

BEGIN
  EXECUTE IMMEDIATE 'ALTER PLUGGABLE DATABASE ORCLPDB OPEN';
EXCEPTION
  WHEN OTHERS THEN
    IF SQLCODE != -65019 THEN
      RAISE;
    END IF;
END;
/

ALTER PLUGGABLE DATABASE ORCLPDB SAVE STATE;

ALTER SESSION SET CONTAINER = ORCLPDB;

BEGIN
  EXECUTE IMMEDIATE 'DROP USER school_user CASCADE';
EXCEPTION
  WHEN OTHERS THEN
    IF SQLCODE != -1918 THEN
      RAISE;
    END IF;
END;
/

CREATE USER school_user IDENTIFIED BY School123
  DEFAULT TABLESPACE users
  TEMPORARY TABLESPACE temp
  QUOTA UNLIMITED ON users
  ACCOUNT UNLOCK;

GRANT CREATE SESSION,
      CREATE TABLE,
      CREATE VIEW,
      CREATE SEQUENCE,
      CREATE PROCEDURE,
      CREATE TRIGGER
  TO school_user;

ALTER SESSION SET CURRENT_SCHEMA = school_user;

BEGIN
  FOR t IN (SELECT column_value AS tname
            FROM TABLE(sys.odcivarchar2list('ENROLLMENT', 'COURSE', 'STUDENT')))
  LOOP
    BEGIN
      EXECUTE IMMEDIATE 'DROP TABLE school_user.' || t.tname || ' CASCADE CONSTRAINTS PURGE';
    EXCEPTION
      WHEN OTHERS THEN
        IF SQLCODE != -942 THEN
          RAISE;
        END IF;
    END;
  END LOOP;
END;
/

CREATE TABLE student (
  id          VARCHAR2(10)
    CONSTRAINT student_pk PRIMARY KEY,
  first_name  VARCHAR2(64)  NOT NULL,
  last_name   VARCHAR2(64)  NOT NULL,
  email       VARCHAR2(128)
    CONSTRAINT student_email_uq UNIQUE
);

CREATE TABLE course (
  id     INT
    GENERATED ALWAYS AS IDENTITY
    CONSTRAINT course_pk PRIMARY KEY,
  code   VARCHAR2(10)  NOT NULL
    CONSTRAINT course_code_uq UNIQUE,
  title  VARCHAR2(100) NOT NULL,
  units  NUMBER(1)     DEFAULT 3
    CONSTRAINT chk_units CHECK (units BETWEEN 1 AND 6)
);

CREATE TABLE enrollment (
  id           INT
    GENERATED ALWAYS AS IDENTITY
    CONSTRAINT enrollment_pk PRIMARY KEY,
  student_id   VARCHAR2(10) NOT NULL,
  course_id    INT          NOT NULL,
  enrolled_on  DATE         DEFAULT SYSDATE,
  CONSTRAINT fk_enroll_student
    FOREIGN KEY (student_id) REFERENCES student(id),
  CONSTRAINT fk_enroll_course
    FOREIGN KEY (course_id)  REFERENCES course(id)
);

ALTER TABLE student ADD (phone VARCHAR2(15));

ALTER TABLE student MODIFY (phone VARCHAR2(20));

ALTER TABLE student
  ADD CONSTRAINT chk_student_email CHECK (email LIKE '%@%');

ALTER TABLE enrollment ADD (status VARCHAR2(10) DEFAULT 'ENROLLED');

INSERT INTO student (id, first_name, last_name, email, phone)
VALUES ('2023-0001', 'Sean', 'Luzano', 'sean@example.com', '09171234567');

INSERT INTO student (id, first_name, last_name, email, phone)
VALUES ('2023-0002', 'Maria', 'Santos', 'maria@example.com', '09181234567');

INSERT INTO student (id, first_name, last_name, email)
VALUES ('2023-0003', 'Juan', 'Dela Cruz', 'juan@example.com');

INSERT INTO course (code, title, units) VALUES ('ITP55',  'Database Systems', 3);
INSERT INTO course (code, title, units) VALUES ('ITP60',  'Web Development',  3);
INSERT INTO course (code, title)        VALUES ('ITP70',  'Capstone Project');

INSERT INTO enrollment (student_id, course_id)
VALUES ('2023-0001', (SELECT id FROM course WHERE code = 'ITP55'));

INSERT INTO enrollment (student_id, course_id)
VALUES ('2023-0001', (SELECT id FROM course WHERE code = 'ITP60'));

INSERT INTO enrollment (student_id, course_id)
VALUES ('2023-0002', (SELECT id FROM course WHERE code = 'ITP55'));

COMMIT;

select * from enrollment;

SELECT *
FROM student
CROSS JOIN enrollment
ORDER BY student.id;

SELECT s.id, s.first_name, s.last_name, c.code, c.title, e.enrolled_on
FROM   student s
JOIN   enrollment e ON e.student_id = s.id
JOIN   course c     ON c.id = e.course_id
ORDER  BY s.id, c.code;
