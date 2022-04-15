--liquibase formatted sql
--changeset Yevhen Danylov:create-developer-skill-table

CREATE TABLE developer_skill
(
    developer_id INT NOT NULL,
    skill_id     INT NOT NULL
);
