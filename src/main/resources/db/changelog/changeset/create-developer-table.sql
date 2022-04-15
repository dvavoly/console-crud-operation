--liquibase formatted sql
--changeset Yevhen Danylov:create-developer-table

CREATE TABLE developer
(
    id         SERIAL PRIMARY KEY NOT NULL,
    first_name VARCHAR(200),
    last_name  VARCHAR(200),
    status     VARCHAR(8) DEFAULT 'ACTIVE'
)