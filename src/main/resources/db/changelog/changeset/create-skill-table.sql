--liquibase formatted sql
--changeset Yevhen Danylov:create-skill-table

CREATE TABLE skill
(
    id         SERIAL       NOT NULL PRIMARY KEY,
    skill_name VARCHAR(255) NOT NULL
);
