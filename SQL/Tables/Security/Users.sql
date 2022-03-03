USE DTX;

CREATE TABLE users
(
    UUID     VARCHAR(255) NOT NULL UNIQUE PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    `role`   VARCHAR(255) NOT NULL,
    fName    VARCHAR(255) NOT NULL,
    lName    VARCHAR(255) NOT NULL,
    enabled BIT NOT NULL DEFAULT 1
);

INSERT INTO users values ('a82c5d96-0dae-4e25-9da4-cd8f6ed44c44', 'stomkins', '$2a$10$6rjGKQe7pc9GQvvDtlvwDOGB2hoN/GyvubK.5EhI8a5Tio8Md6X/u', 'ROLE_ADMIN', 1, 'Stephen', 'Tomkins');

