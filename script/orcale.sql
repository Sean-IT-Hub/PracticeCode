alter session set container = ORCLPDB;

Drop user school_user cascade;

CREATE USER school_user
IDENTIFIED BY School123;

GRANT CREATE SESSION, CREATE TABLE
TO school_user;