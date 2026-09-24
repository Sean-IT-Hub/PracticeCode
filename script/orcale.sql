alter session set container = ORCLPDB;

CREATE USER school_user
IDENTIFIED BY School123;

GRANT CREATE SESSION, CREATE TABLE
TO school_user;

SELECT username
FROM dba_users
WHERE username = 'SCHOOL_USER';

ALTER USER school_user
QUOTA UNLIMITED ON users;
