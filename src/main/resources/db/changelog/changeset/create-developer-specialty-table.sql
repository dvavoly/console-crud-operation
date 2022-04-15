--liquibase formatted sql
--changeset Yevhen Danylov:create-developer-specialty-table

CREATE TABLE developer_specialty
(
    developer_id INT NOT NULL,
    specialty_id INT NOT NULL
);
