--liquibase formatted sql
--changeset Yevhen Danylov:create-specialty-table

CREATE TABLE specialty
(
    id             SERIAL       NOT NULL PRIMARY KEY,
    specialty_name VARCHAR(255) NOT NULL
);
