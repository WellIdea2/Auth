CREATE TABLE users
(
    id            BINARY(16)   NOT NULL PRIMARY KEY,
    username      VARCHAR(255) NOT NULL ,
    email         VARCHAR(255) NOT NULL UNIQUE,
    password      VARCHAR(255) NOT NULL,
    role          VARCHAR(255) NOT NULL
);
